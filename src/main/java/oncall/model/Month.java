package oncall.model;

import oncall.exception.CustomIllegalArgumentException;
import oncall.utils.ErrorMessage;

public class Month {

    private final int value;

    public Month(int value) {
        this.value = value;
    }

    public static Month of(String month) {
        try {
            int parseMonth = Integer.parseInt(month);
            validInput(parseMonth);
            return new Month(parseMonth);
        } catch (NumberFormatException e) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_INPUT);
        }
    }


    public static void validInput(int month) {
        if (month <= 0 || month >= 13) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_INPUT);
        }
    }

    public int getValue() {
        return value;
    }
}
