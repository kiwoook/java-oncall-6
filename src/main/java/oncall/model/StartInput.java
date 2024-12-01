package oncall.model;

import oncall.utils.StringUtils;

public record StartInput(Month month, DayOfTheWeek dayOfTheWeek) {

    public static StartInput of(String input) {
        String[] split = StringUtils.split(",", input, 2);

        Month month = Month.of(split[0]);
        DayOfTheWeek dayOfTheWeek = DayOfTheWeek.from(split[1]);
        return new StartInput(month, dayOfTheWeek);
    }
}
