package worker;

import data.Data;
import enums.State;
import model.Bill;
import service.Service;
import util.Util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BillScheduleWorker implements Runnable {
    private Bill bill;

    public BillScheduleWorker(Bill bill) {
        this.bill = bill;
    }

    @Override
    public void run() {
        Map<String, String> result = new HashMap<>();
        ;
        try {
            System.out.println("Pay BillScheduleWorker with Bill Id: {" + bill.getId() + "}");
            result = this.payBill(bill);
        } catch (Exception e) {

        } finally {
            System.out.println("Pay bill id {" + bill.getId() + "}: " + result.get("messeage"));
            addHistory(bill, result);
        }
    }

    private Map<String, String> payBill(Bill bill) {
        Map<String, String> result = new HashMap<>();
        if (vaild(bill)) {
            Double balanceNew = Service.decreaseToAccount(bill.getAmount(), bill.getAccountId());
            if (Util.isNotNullAndGreaterThanZero(balanceNew)) {
                bill.setState(State.PROCESSED);
                Data.bills.put(bill.getId(), bill);
                result.put("messeage", "PROCESSED balanceNew : {" + balanceNew + "}");
                result.put("state", State.PROCESSED.toString());
            } else {
                result.put("messeage", "Sorry !! Not enough fund to proceed with payment");
                result.put("state", State.PENDING.toString());
            }
        } else {
            result.put("messeage", "Sorry! Bill doesn't to proceed with payment");
            result.put("state", State.PENDING.toString());
        }
        return result;
    }

    private void addHistory(Bill bill, Map<String, String> result) {
        Data.addHistory(bill, new Date(), result.get("messeage"), State.getByValue(result.get("state")));
    }

    private boolean vaild(Bill bill) {
        if (Data.bills.containsKey(bill.getId())) {
            return Data.bills.get(bill.getId()).getState() == State.NOT_PAID;
        }
        return false;
    }
}
