package oncall.model;

import java.util.Arrays;

public enum Holiday {
    A(1, 1),
    B(3, 1),
    C(5, 5),
    D(6, 6),
    E(8, 15),
    F(10, 3),
    G(10, 9),
    H(12, 25);

    private final int month;
    private final int day;

    Holiday(int month, int day) {
        this.month = month;
        this.day = day;
    }

    public static boolean checkHoliday(int month, int day) {
        return Arrays.stream(values())
                .anyMatch((today) -> today.isHoliday(month, day));
    }

    public boolean isHoliday(int month, int day) {
        return this.month == month && this.day == day;
    }
}
