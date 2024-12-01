package oncall.view;

import java.time.DayOfWeek;
import java.util.List;
import oncall.model.DayOfTheWeek;
import oncall.model.Holiday;

public class OutputViewer {

    private static final String ERROR_SIGN = "[ERROR] ";

    public void printError(Exception e) {
        System.out.println(e.getMessage());
    }

    public void printResult(int month, DayOfWeek dayOfWeek, List<String> names) {

        for (int dayIdx = 0; dayIdx <= names.size(); dayIdx++) {
            if (isHoliday(month, dayIdx + 1, dayOfWeek)) {
                System.out.println(month + "월 " + dayIdx + 1 + "일" + dayOfWeek + "(휴일)" + names.get(dayIdx));
            }

            System.out.println(month + "월 " + dayIdx + 1 + "일" +);
        }
    }


    private boolean isHoliday(int month, int day, DayOfWeek dayOfWeek) {
        if (!DayOfTheWeek.isWeekends(dayOfWeek) && Holiday.isHoliday(month, day)) {

        }

    }
}
