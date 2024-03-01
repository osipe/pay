package enums;

import java.util.Arrays;

public enum State {
    NOT_PAID("NOT_PAID", "NOT_PAID"),
    PENDING("PENDING", "PENDING"),
    PROCESSED("PROCESSED", "PROCESSED");
    private String value;
    private String name;

    State(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static State getByValue(String value) {
        return Arrays.stream(State.values()).filter(e -> e.value.equals(value)).findFirst().orElse(null);
    }
}
