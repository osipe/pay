package service;

import data.Data;
import enums.State;
import model.Account;
import model.Bill;
import util.Util;

import java.util.*;

public class PayService {
    public static Account reLoadAccount(Long accountId) {
        return Data.accounts.get(accountId);
    }

    public static void addFundToAccount(Double amount, Long accountId) {
        if (Util.isNotNullAndGreaterThanZero(amount) && Util.isNotNullAndGreaterThanZero(accountId)) {
            Double balanceNew = Service.addFundToAccount(amount, accountId);
            System.out.println("Your avalible balance: " + balanceNew);
        } else {
            System.out.println("Sorry! Bad Reuqest.");
        }
    }

    public static void listBill(Long accountId, State stateBill) {
        System.out.println("No.     Amount      DueDate     State       Provider");
        Data.bills.values().stream().filter(bill ->
                accountId == bill.getAccountId() && stateBill == bill.getState()
        ).forEach(bill -> {
            System.out.println(bill.toStringPrintln());
        });
    }

    public static void pay(Long accountId, Set<Long> billIds) {
        Double amount = 0.0;
        List<Bill> bills = new ArrayList<>();
        boolean error = false;
        for (Long billId : billIds) {
            if (Data.bills.containsKey(billId)) {
                Bill bill = Data.bills.get(billId);
                if (bill.getState() != State.NOT_PAID || bill.getAccountId() != accountId) {
                    System.out.println("Sorry! Bill doesn't to proceed with payment with id {" + billId + "}.");
                    error = true;
                    break;
                } else {
                    bills.add(bill);
                    amount += bill.getAmount();
                }
            } else {
                error = true;
                System.out.println("Sorry! Not found a bill with id {" + billId + "}.");
                break;
            }
        }
        if (!error && Util.isNotNullAndGreaterThanZero(amount)) {
            updateBillAndAddHistory(amount, accountId, bills);
        } else {
            //TODO
        }

    }

    private static void updateBillAndAddHistory(Double amount, Long accountId, List<Bill> bills) {
        Double balanceNew = Service.decreaseToAccount(amount, accountId);
        Date payDate = new Date();
        if (Util.isNotNullAndGreaterThanZero(balanceNew)) {
            for (Bill bill : bills) {
                bill.setState(State.PROCESSED);
                Data.bills.put(bill.getId(), bill);
                Data.addHistory(bill, payDate, "PROCESSED balanceNew : {" + balanceNew + "}", State.PROCESSED);
            }
        } else {
            for (Bill bill : bills) {
                Data.addHistory(bill, payDate, "Sorry !! Not enough fund to proceed with payment", State.PENDING);
            }
        }
    }

    public static void dueBills(Long accountId, State stateBill) {
        System.out.println("No.     Amount      DueDate     State       Provider");
        Data.bills.values().stream().filter(bill ->
                accountId == bill.getAccountId() && stateBill == bill.getState()
        ).sorted(Comparator.comparingLong(Bill::getDueDateTime)).forEach(bill -> {
            System.out.println(bill.toStringPrintln());
        });
    }

    public static void listHistory(Long accountId) {
        System.out.println("No.  Amount   PaymentDate  State       BillId");
        Data.histories.values().stream().filter(bill ->
                accountId == bill.getAccountId()
        ).forEach(history -> {
            System.out.println(history.toStringPrintln());
        });

    }

    public static void schedule(Long accountId, Set<Long> billIds, String datePay) {
        List<Bill> bills = new ArrayList<>();
        boolean error = false;
        for (Long billId : billIds) {
            if (Data.bills.containsKey(billId)) {
                Bill bill = Data.bills.get(billId);
                if (bill.getState() != State.NOT_PAID || bill.getAccountId() != accountId) {
                    System.err.println("Sorry! Bill doesn't to proceed with payment.");
                    error = true;
                    break;
                } else {
                    bills.add(bill);
                }
            } else {
                error = true;
                System.err.println("Sorry! Not found a bill with id {" + billId + "}.");
                break;
            }
        }
        if (!error) {
            addScheduleBill(datePay, bills);
        } else {
            //TODO
        }
    }

    private static void addScheduleBill(String datePay, List<Bill> bills) {
        Map<Long, Bill> billMap = new HashMap<>();
        if (Data.billSchedule != null && Data.billSchedule.containsKey(datePay)) {
            billMap = Data.billSchedule.get(datePay);
        }
        for (Bill bill : bills) {
            billMap.put(bill.getId(), bill);
        }
        Data.billSchedule.put(datePay, billMap);
        System.out.println("Request add schedule bill is PROCESSED");
    }
}
