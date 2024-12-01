package oncall.view;

import java.time.DayOfWeek;
import java.util.List;
import java.util.StringJoiner;
import oncall.model.DayOfTheWeek;
import oncall.model.Holiday;

public class OutputViewer {

    private static final String ERROR_SIGN = "[ERROR] ";

    public void printError(Exception e) {
        System.out.println(ERROR_SIGN + e.getMessage());
    }

    public void printResult(int month, DayOfWeek startdayOfWeek, List<String> names) {
        System.out.println();
        for (int dayIdx = 0; dayIdx < names.size(); dayIdx++) {
            DayOfWeek dayOfWeek = startdayOfWeek.plus(dayIdx);

            StringJoiner joiner = new StringJoiner(" ")
                    .add(month + "월")
                    .add((dayIdx + 1) + "일")
                    .add(toDayOfWeek(month, dayIdx, dayOfWeek))
                    .add(names.get(dayIdx));

            System.out.println(joiner);
        }
    }

    private String toDayOfWeek(int month, int dayIdx, DayOfWeek dayOfWeek) {
        if (isHoliday(month, dayIdx + 1, dayOfWeek)) {
            return DayOfTheWeek.toDayOfWeek(dayOfWeek) + "(휴일)";
        }
        return DayOfTheWeek.toDayOfWeek(dayOfWeek);
    }

    private boolean isHoliday(int month, int day, DayOfWeek dayOfWeek) {
        return !DayOfTheWeek.isWeekends(dayOfWeek) && Holiday.isHoliday(month, day);
    }
}
