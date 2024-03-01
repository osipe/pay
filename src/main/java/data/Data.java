package data;

import enums.State;
import model.Account;
import model.Bill;
import model.History;

import java.util.Date;
import java.util.Map;

public class Data {
    public static Map<Long, Account> accounts;
    public static Map<Long, Bill> bills;
    public static Map<Long, History> histories;
    public static Long counterHistoryId = 1L;
    public static Map<String,Map<Long, Bill>> billSchedule;

    public static void addHistory(Bill bill, Date payDate, String messeage, State state) {
        histories.put(counterHistoryId, new History(counterHistoryId, payDate, state, bill, messeage));
        counterHistoryId++;
    }

}
