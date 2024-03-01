package util;

public class Util {
    public static boolean isNotNullAndGreaterThanZero(Double value) {
        return value != null && value > 0;
    }
    public static boolean isNotNullAndGreaterThanZero(Long value) {
        return value != null && value > 0;
    }

}
