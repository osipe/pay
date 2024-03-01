package model;

import enums.BillType;
import enums.State;

import java.text.SimpleDateFormat;
import java.util.Date;

public class History {
    private Long id;
    private Long billId;
    private Long accountId;
    private BillType type;
    private Double amount;
    private Date dueDate;
    private Date payDate;
    private State state;
    private String providerName;
    private String providerCode;
    private String messeage;

    public History(Long id,Date payDate,State state,Bill bill,String messeage) {
        this.id = id;
        this.billId = bill.getId();
        this.accountId = bill.getAccountId();
        this.type = bill.getType();
        this.amount = bill.getAmount();
        this.dueDate = bill.getDueDate();
        this.payDate = payDate;
        this.state = state;
        this.providerName = bill.getProviderName();
        this.providerCode = bill.getProviderCode();
        this.messeage = messeage;
    }
    public History(Long id, Long billId, Long accountId, BillType type, Double amount, Date dueDate, Date payDate, State state, String providerName, String providerCode,String messeage) {
        this.id = id;
        this.billId = billId;
        this.accountId = accountId;
        this.type = type;
        this.amount = amount;
        this.dueDate = dueDate;
        this.payDate = payDate;
        this.state = state;
        this.providerName = providerName;
        this.providerCode = providerCode;
        this.messeage = messeage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public BillType getType() {
        return type;
    }

    public void setType(BillType type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    public String getMesseage() {
        return messeage;
    }

    public void setMesseage(String messeage) {
        this.messeage = messeage;
    }
    public String toStringPrintln(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.id);
        stringBuilder.append("\t");
        stringBuilder.append(this.amount);
        stringBuilder.append("\t");
        stringBuilder.append(this.payDate != null ? sdf.format(this.payDate) : "");
        stringBuilder.append("\t");
        stringBuilder.append(this.state);
        stringBuilder.append("\t");
        stringBuilder.append(this.billId);
        return stringBuilder.toString();
    }
}
