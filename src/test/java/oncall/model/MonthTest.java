package oncall.model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import oncall.exception.CustomIllegalArgumentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MonthTest {

    @DisplayName("정상적인 변환")
    @Test
    void test1() {
        for (int month = 1; month <= 12; month++) {
            String s = String.valueOf(month);
            Month.of(s);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "일", "13"})
    @DisplayName("비정상적인 입력 시 에러 반환")
    void test2(String input) {
        assertThrows(CustomIllegalArgumentException.class, () -> Month.of(input));
    }

}
