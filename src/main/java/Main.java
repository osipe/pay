import data.Data;
import enums.BillType;
import enums.State;
import model.Account;
import model.Bill;
import schedule.Scheduler;
import service.PayService;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Account acc = initData();
        String commandEx = "On";
        System.out.println("Welcome to Service Payment, " + acc.getName());
        Scanner scanner = new Scanner(System.in);
        while (!commandEx.equals("Exit")) {
            System.out.println("============Please choose service============");
            System.out.println("CASH_IN: add fund into balance account");
            System.out.println("LIST_BILL: View all list bill");
            System.out.println("LIST_BILL_DUE_DATE: check bill sort due date");
            System.out.println("PAY: pay your bill");
            System.out.println("LIST_PAYMENT: Check payment transaction");
            System.out.println("SCHEDULE: ");
            System.out.println("VIEW_TASK_SCHEDULE: ");
            System.out.println("Exit: exit");
            System.out.print("Press command: ");
            commandEx = scanner.nextLine();
            //System.out.print("hasNext: " + );
            switch (commandEx) {
                case "CASH_IN":
                    cachIn(scanner, acc);
                    break;
                case "LIST_BILL":
                    PayService.listBill(acc.getId(), State.NOT_PAID);
                    break;
                case "LIST_BILL_DUE_DATE":
                    PayService.dueBills(acc.getId(), State.NOT_PAID);
                    break;
                case "PAY":
                    pay(scanner, acc);
                    break;
                case "LIST_PAYMENT":
                    PayService.listHistory(acc.getId());
                    break;
                case "SCHEDULE":
                    schedule(scanner, acc);
                    break;
                case "VIEW_TASK_SCHEDULE":
                    initScheduler(scanner);
                    break;
                case "Exit":
                    System.out.println("Good bye !");
                    break;
                default:
                    System.err.println("Invalid commnad");
                    break;
            }
        }

    }

    private static void initScheduler(Scanner scanner) {
        try {
            Scheduler.initScheduler();
            String commandEx = "On";
            while (!commandEx.equals("Quit")) {
                System.out.println("Quit : exit view schedulerTask");
                commandEx = scanner.nextLine();
                switch (commandEx) {
                    case "Quit":
                        break;
                    default:
                        System.err.println("Invalid commnad");
                        break;
                }
            }
        } catch (Exception e) {
            System.err.println("Invalid commandEx");
        }
    }

    private static void pay(Scanner scanner, Account acc) {
        System.out.println("List id (Example: 1,2):");
        try {
            String ids = scanner.nextLine();
            Set<Long> billIds = Arrays.stream(ids.split(",")).map(id -> Long.valueOf(id)).collect(Collectors.toSet());
            PayService.pay(acc.getId(), billIds);
        } catch (Exception e) {
            System.err.println("Invalid bill id");
        }
    }

    private static void cachIn(Scanner scanner, Account acc) {
        System.out.println("Amount: ");
        try {
            Double amount = scanner.nextDouble();
            PayService.addFundToAccount(amount, acc.getId());
        } catch (Exception e) {
            System.err.println("Invalid Amount");
        }
    }

    private static void schedule(Scanner scanner, Account acc) {

        try {
            System.out.println("List Bill id (Example: 1,2):");
            String ids = scanner.nextLine();
            Set<Long> billIds = Arrays.stream(ids.split(",")).map(id -> Long.valueOf(id)).collect(Collectors.toSet());
            System.out.println("Pay Date:");
            String payDue = scanner.nextLine();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date payDate = sdf.parse(payDue);
            if (payDate != null) {
                PayService.schedule(acc.getId(), billIds, payDue);
            }
        } catch (Exception e) {
            System.err.println("Invalid info");
        }
    }

    public static Account initData() {
        Account acc = new Account(1L, "Annn", 0.0);
        Data.accounts = new HashMap<>();
        Data.accounts.put(1L, acc);
        Data.bills = new HashMap<>();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 5);
        Data.bills.put(1L, new Bill(1L, 1L, BillType.ELECTRIC, 200000.0, cal.getTime(), State.NOT_PAID, "EVN HCMC", "EVN HCMC"));
        cal.add(Calendar.DATE, 10);
        Data.bills.put(2L, new Bill(2L, 1L, BillType.WATER, 175000.0, cal.getTime(), State.NOT_PAID, "SAVACO HCMC", "SAVACO HCMC"));
        cal.add(Calendar.DATE, -5);
        Data.bills.put(3L, new Bill(3L, 1L, BillType.INTERNET, 800000.0, cal.getTime(), State.NOT_PAID, "VNPT", "VNPT"));
        Data.histories = new HashMap<>();
        Data.billSchedule = new HashMap<>();
        return acc;
    }
}