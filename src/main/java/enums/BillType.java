package enums;

public enum BillType {
    ELECTRIC("ELECTRIC","ELECTRIC"),
    WATER("WATER","WATER"),
    INTERNET("INTERNET","INTERNET");
    private String value;
    private String name;

    BillType(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
