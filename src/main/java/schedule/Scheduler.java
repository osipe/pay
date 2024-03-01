package schedule;

import data.Data;
import model.Bill;
import worker.BillScheduleWorker;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Scheduler {
    public static String status = "Off";

    public static void initScheduler() {
        System.out.println("===initScheduler=======");
        Thread myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (Scheduler.status != null && Scheduler.status.equals("On")) {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            String dateString = sdf.format(new Date());
                            System.out.println("===Run Scheduler with pay date: {" + dateString + "}");
                            int corePoolSize = 4;
                            int maximumPoolSize = 8;
                            int queueCapacity = 100;
                            final ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize,
                                    maximumPoolSize,
                                    10,
                                    TimeUnit.SECONDS, new ArrayBlockingQueue<>(queueCapacity));
                            try {
                                if (Data.billSchedule != null && Data.billSchedule.containsKey(dateString)) {
                                    List<Bill> billSorted = Data.billSchedule.get(dateString).values().stream().sorted(Comparator.comparingLong(Bill::getDueDateTime)).collect(Collectors.toList());
                                    executor.execute(new BillScheduleWorker(billSorted));
                                    Data.billSchedule.remove(dateString);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        Thread.sleep(15000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        myThread.start();
    }
}
