package service;

import data.Data;
import model.Account;

public class Service {
    public synchronized static Double addFundToAccount(Double amount, Long accountId) {
        try {
            if (!Data.accounts.isEmpty() && Data.accounts.containsKey(accountId)) {
                Account acc = Data.accounts.get(accountId);
                Double balanceNew = acc.getBalance() + amount;
                acc.setBalance(balanceNew);
                Data.accounts.put(accountId, acc);
                return balanceNew;
            } else {
                System.out.println("Sorry !! Not found Account");
            }
        } catch (Exception e) {
            System.out.println("System error !!");
        }
        return null;
    }

    public synchronized static Double decreaseToAccount(Double amount, Long accountId) {
        try {
            if (!Data.accounts.isEmpty() && Data.accounts.containsKey(accountId)) {
                Account acc = Data.accounts.get(accountId);
                if (acc.getBalance() >= amount) {
                    Double balanceNew = acc.getBalance() - amount;
                    acc.setBalance(balanceNew);
                    Data.accounts.put(accountId, acc);
                    System.out.println("Your avalible balance: " + balanceNew);
                    return balanceNew;
                } else {
                    System.out.println("Sorry !! Not enough fund to proceed with payment");
                }
            } else {
                System.out.println("Sorry !! Not found Account");
            }
        } catch (Exception e) {
            System.out.println("System error !!");
        }
        return null;
    }


}
