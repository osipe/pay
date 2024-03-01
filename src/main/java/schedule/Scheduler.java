package schedule;

import data.Data;
import model.Bill;
import worker.BillScheduleWorker;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Scheduler {
    public static void initScheduler() {
        System.out.println("===initScheduler=======");
        Thread myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        int corePoolSize = 4;
                        int maximumPoolSize = 8;
                        int queueCapacity = 100;
                        final ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize,
                                maximumPoolSize,
                                10,
                                TimeUnit.SECONDS, new ArrayBlockingQueue<>(queueCapacity));
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        String dateString = sdf.format(new Date());
                        System.out.println("===Run Scheduler with pay date: {" + dateString + "}");
                        try {
                            if (Data.billSchedule != null && Data.billSchedule.containsKey(dateString)) {
                                Data.billSchedule.get(dateString).values().stream().sorted(Comparator.comparingLong(Bill::getDueDateTime)).forEach(bill ->
                                {
                                    executor.execute(new BillScheduleWorker(bill));

                                });
                                Data.billSchedule.remove(dateString);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            Thread.sleep(15000);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        myThread.start();
    }
}
