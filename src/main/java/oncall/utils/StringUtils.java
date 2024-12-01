package oncall.utils;

import oncall.exception.CustomIllegalArgumentException;

public class StringUtils {

    private static final String OR = "|";

    private StringUtils() {
    }

    public static String[] split(String regex, String input, Integer fieldCount) {
        if (regex == null || input == null || input.isBlank() || input.startsWith(regex)) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_INPUT.getMessage());
        }

        String[] split = input.split(regex);
        if (fieldCount != null && split.length != fieldCount) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_INPUT.getMessage());
        }
        return split;
    }


}

