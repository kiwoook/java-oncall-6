package oncall.utils;

import oncall.exception.CustomIllegalArgumentException;

public class StringUtils {

    private StringUtils() {
    }

    public static String[] split(String regex, String input, Integer fieldCount) {
        if (regex == null || input == null || input.isBlank() || input.endsWith(regex)) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_INPUT.getMessage());
        }

        String[] split = input.split(regex);
        if (fieldCount != null && split.length != fieldCount) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_INPUT.getMessage());
        }
        return split;
    }


}

