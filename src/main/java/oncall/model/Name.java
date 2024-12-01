package oncall.model;

import java.util.Objects;
import oncall.exception.CustomIllegalArgumentException;
import oncall.utils.ErrorMessage;

public class Name {

    private final String value;

    public Name(String value) {
        validName(value);
        this.value = value;
    }

    public static Name from(String value) {
        return new Name(value);
    }

    public void validName(String value) {
        if (value == null || value.isBlank() || value.length() > 5) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_INPUT);
        }
    }

    public String getValue() {
        return value;
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

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return "Name{" +
                "value='" + value + '\'' +
                '}';
    }
}
