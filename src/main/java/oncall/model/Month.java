package oncall.model;

import oncall.exception.CustomIllegalArgumentException;
import oncall.utils.ErrorMessage;

public record Month(int value) {

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
}
