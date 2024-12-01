package oncall.model;

import java.util.Arrays;
import oncall.utils.StringUtils;

public record StartInput(Month month, DayOfTheWeek dayOfTheWeek) {

    public static StartInput of(String input) {
        String[] split = StringUtils.split(",", input, 2);

        System.out.println(Arrays.toString(split));
        Month month = Month.of(split[0]);
        DayOfTheWeek dayOfTheWeek = DayOfTheWeek.from(split[1]);
        return new StartInput(month, dayOfTheWeek);
    }
}
