package oncall.model;

import java.util.Arrays;
import oncall.exception.CustomIllegalArgumentException;
import oncall.utils.ErrorMessage;

public enum DayOfTheWeek {
    MON("월"),
    TUE("화"),
    WED("수"),
    THU("목"),
    FRI("금"),
    SAT("토"),
    SUN("일");

    public final String value;

    DayOfTheWeek(String value) {
        this.value = value;
    }

    public static DayOfTheWeek of(String input) {
        return Arrays.stream(values()).filter((v) -> v.value.equals(input)).findAny()
                .orElseThrow(() -> new CustomIllegalArgumentException(
                        ErrorMessage.INVALID_INPUT));
    }

}
