package oncall.model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import oncall.exception.CustomIllegalArgumentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class StartInputTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"5,월,", ",5,월", "오,월", "5,1", "5, 월", "5,MON", "13,월", "5,월,월", ","})
    @DisplayName("잘못된 입력을 하면 에러를 반환한다.")
    void test1(String input) {
        assertThrows(CustomIllegalArgumentException.class, () -> StartInput.of(input));
    }


}
