import data.Data;
import model.Account;
import org.junit.Assert;
import service.PayService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Test {
    @org.junit.Test
    public void testCachIn() {
        System.out.println("==========Test testCachIn==========");
        Account acc =  Main.initData();
        Double amount1 = 100000.0;
        Double amount2 = 175000.0;
        System.out.println("CASH_IN 1: " + amount1);
        PayService.addFundToAccount(amount1, acc.getId());
        System.out.println("CASH_IN 2: " + amount2);
        PayService.addFundToAccount(amount2, acc.getId());
        Double expectedSum = 275000.0;
        Assert.assertEquals(expectedSum, Data.accounts.get(acc.getId()).getBalance());
        System.out.println("==========End testCachIn==========");
    }
    @org.junit.Test
    public void testPay() {
        System.out.println("==========Test pay bill==========");
        Account acc =  Main.initData();
        Double amount = 1000000.0;
        System.out.println("CASH_IN : " + amount);
        PayService.addFundToAccount(amount, acc.getId());
        Set<Long> billIds = new HashSet<>();
        billIds.add(1L);
        System.out.println("PAY Bill Id {1} : ");
        PayService.pay(acc.getId(), billIds);
        Set<Long> billIds_2 = new HashSet<>();
        billIds_2.add(2L);
        billIds_2.add(3L);
        System.out.println("PAY Bill Id {2,3} : ");
        PayService.pay(acc.getId(), billIds);
        Double expectedSum = 800000.0;
        Assert.assertEquals(expectedSum, Data.accounts.get(acc.getId()).getBalance());
        System.out.println("==========End Test pay bill==========");
    }
}
