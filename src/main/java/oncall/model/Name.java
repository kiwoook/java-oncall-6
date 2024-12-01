package oncall.model;

import java.util.Objects;
import oncall.exception.CustomIllegalArgumentException;
import oncall.utils.ErrorMessage;

public record Name(String value) {

    public Name {
        validName(value);
    }

    public static Name from(String value) {
        return new Name(value);
    }

    public void validName(String value) {
        if (value == null || value.isBlank() || value.length() > 5) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_INPUT);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Name name = (Name) o;
        return Objects.equals(value, name.value);
    }
}
