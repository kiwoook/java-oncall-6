package oncall.model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import oncall.exception.CustomIllegalArgumentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class NameTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"123456"})
    @DisplayName("이름이 5자를 초과하거나 공백이면 에러를 발생한다")
    void test1(String input) {
        assertThrows(CustomIllegalArgumentException.class, () -> Name.from(input));
    }
}
