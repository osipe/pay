package model;

public class Account {
    private Long id;
    private String name;
    private Double balance;

    public Account(Long id, String name, Double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance != null ? balance : 0;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
