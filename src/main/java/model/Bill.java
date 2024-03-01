package model;

import enums.BillType;
import enums.State;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Bill {
    private Long id;
    private Long accountId;
    private BillType type;
    private Double amount;
    private Date dueDate;
    private Long dueDateTime;
    private State state;
    private String providerName;
    private String providerCode;

    public Bill(Long id, Long accountId, BillType type, Double amount, Date dueDate, State state, String providerName, String providerCode) {
        this.id = id;
        this.accountId = accountId;
        this.type = type;
        this.amount = amount;
        this.dueDate = dueDate;
        this.dueDateTime = dueDate != null ? dueDate.getTime() : 0L;
        this.state = state;
        this.providerName = providerName;
        this.providerCode = providerCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getDueDateTime() {
        return dueDateTime;
    }

    public void setDueDateTime(Long dueDateTime) {
        this.dueDateTime = dueDateTime;
    }

    public String toStringPrintln(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.id);
        stringBuilder.append("\t");
        stringBuilder.append(this.type);
        stringBuilder.append("\t");
        stringBuilder.append(this.amount);
        stringBuilder.append("\t");
        stringBuilder.append(this.dueDate != null ? sdf.format(this.dueDate) : "");
        stringBuilder.append("\t");
        stringBuilder.append(this.state);
        stringBuilder.append("\t");
        stringBuilder.append(this.providerName);
        return stringBuilder.toString();
    }
}
