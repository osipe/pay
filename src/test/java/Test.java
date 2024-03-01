import data.Data;
import enums.State;
import model.Account;
import org.junit.Assert;
import schedule.Scheduler;
import service.PayService;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Test {
//    @org.junit.Test
//    public void testCachIn() {
//        System.out.println("==========Test testCachIn==========");
//        Account acc =  Main.initData();
//        Double amount1 = 100000.0;
//        Double amount2 = 175000.0;
//        System.out.println("CASH_IN 1: " + amount1);
//        PayService.addFundToAccount(amount1, acc.getId());
//        System.out.println("CASH_IN 2: " + amount2);
//        PayService.addFundToAccount(amount2, acc.getId());
//        Double expectedSum = 275000.0;
//        Assert.assertEquals(expectedSum, Data.accounts.get(acc.getId()).getBalance());
//        System.out.println("==========End testCachIn==========");
//    }
//    @org.junit.Test
//    public void testPay() {
//        System.out.println("==========Test pay bill==========");
//        Account acc =  Main.initData();
//        Double amount = 1000000.0;
//        System.out.println("CASH_IN : " + amount);
//        PayService.addFundToAccount(amount, acc.getId());
//        Set<Long> billIds = new HashSet<>();
//        billIds.add(1L);
//        System.out.println("PAY Bill Id {1} : ");
//        PayService.pay(acc.getId(), billIds);
//        Set<Long> billIds_2 = new HashSet<>();
//        billIds_2.add(2L);
//        billIds_2.add(3L);
//        System.out.println("PAY Bill Id {2,3} : ");
//        PayService.pay(acc.getId(), billIds);
//        Double expectedSum = 800000.0;
//        Assert.assertEquals(expectedSum, Data.accounts.get(acc.getId()).getBalance());
//        System.out.println("==========End Test pay bill==========");
//    }
    @org.junit.Test
    public void testSchedulePay() throws InterruptedException {
        System.out.println("==========Test schedule pay bill==========");
        Account acc =  Main.initData();
        Double amount = 1000000.0;
        System.out.println("CASH_IN : " + amount);
        PayService.addFundToAccount(amount, acc.getId());
        System.out.println("==========LIST BILL ==========");
        PayService.listBill(acc.getId(), State.NOT_PAID);
        System.out.println("==========LIST BILL DUE DATE==========");
        PayService.dueBills(acc.getId(), State.NOT_PAID);
        Set<Long> billIds = new HashSet<>();
        billIds.add(1L);
        billIds.add(1L);
        billIds.add(2L);
        billIds.add(3L);
        System.out.println("Schedule pay bill Id {1,2,3} : ");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = sdf.format(new Date());
        PayService.schedule(acc.getId(), billIds, dateString);
        Double expectedSum = 625000.0;
        Scheduler.initScheduler();
        Thread.sleep(10000);
        System.out.println("==========LIST BILL==========");
        PayService.listBill(acc.getId(), State.NOT_PAID);
        System.out.println("==========LIST PAYMENT==========");
        PayService.listHistory(acc.getId());
        System.out.println("Balance : " + Data.accounts.get(acc.getId()).getBalance());
        Assert.assertEquals(expectedSum, Data.accounts.get(acc.getId()).getBalance());
        System.out.println("==========End Test schedule pay bill==========");
    }
}
