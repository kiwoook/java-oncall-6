package oncall.view;

import java.time.DayOfWeek;
import java.util.List;
import oncall.model.DayOfTheWeek;
import oncall.model.Holiday;

public class OutputViewer {

    private static final String ERROR_SIGN = "[ERROR] ";

    public void printError(Exception e) {
        System.out.println(ERROR_SIGN + e.getMessage());
    }

    public void printResult(int month, DayOfWeek startdayOfWeek, List<String> names) {
        for (int dayIdx = 0; dayIdx < names.size(); dayIdx++) {
            DayOfWeek dayOfWeek = startdayOfWeek.plus(dayIdx);

            if (isHoliday(month, dayIdx + 1, startdayOfWeek)) {
                System.out.println(
                        month + "월 " + (dayIdx + 1) + "일 " + DayOfTheWeek.toDayOfWeek(dayOfWeek) + "(휴일) "
                                + names.get(
                                dayIdx));
            }

            System.out.println(
                    month + "월 " + (dayIdx + 1) + "일 " + DayOfTheWeek.toDayOfWeek(dayOfWeek) + " " + names.get(
                            dayIdx));
        }
    }


    private boolean isHoliday(int month, int day, DayOfWeek dayOfWeek) {
        return !DayOfTheWeek.isWeekends(dayOfWeek) && Holiday.isHoliday(month, day);
    }
}
