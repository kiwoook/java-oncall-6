package oncall.model;

import java.time.DayOfWeek;
import java.util.Arrays;
import oncall.exception.CustomIllegalArgumentException;
import oncall.utils.ErrorMessage;

public enum DayOfTheWeek {
    MON("월", DayOfWeek.MONDAY), TUE("화", DayOfWeek.TUESDAY), WED("수", DayOfWeek.WEDNESDAY), THU("목",
            DayOfWeek.THURSDAY), FRI("금", DayOfWeek.FRIDAY), SAT("토", DayOfWeek.SATURDAY), SUN("일", DayOfWeek.SUNDAY);

    public final String korean;
    private final DayOfWeek dayOfWeek;

    DayOfTheWeek(String korean, DayOfWeek dayOfWeek) {
        this.korean = korean;
        this.dayOfWeek = dayOfWeek;
    }

    public static DayOfTheWeek from(String input) {
        return Arrays.stream(values()).filter(value -> value.korean.equals(input)).findAny()
                .orElseThrow(() -> new CustomIllegalArgumentException(ErrorMessage.INVALID_INPUT));
    }

    public static String toDayOfWeek(DayOfWeek dayOfWeek) {
        return Arrays.stream(values()).filter(value -> value.dayOfWeek.equals(dayOfWeek)).findAny()
                .orElseThrow(IllegalStateException::new).korean;
    }


    public static boolean isWeekends(DayOfWeek dayOfWeek) {
        return dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY);
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
}
