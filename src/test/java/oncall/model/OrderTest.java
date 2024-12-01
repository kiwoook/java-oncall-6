package oncall.model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.StringJoiner;
import oncall.exception.CustomIllegalArgumentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderTest {

    @Test
    @DisplayName("순번 이름에 중복되면 에러를 반환한다.")
    void test1() {
        String input = "수아,수아,혜린";

        Order order = new Order();
        assertThrows(CustomIllegalArgumentException.class, () -> order.addWeekdays(input));
        assertThrows(CustomIllegalArgumentException.class, () -> order.addWeekends(input));
    }

    @ParameterizedTest
    @DisplayName("근무자가 5명 미만이면 에러를 반환한다.")
    @ValueSource(ints = {0, 1, 2, 3, 4})
    void test2(int person) {
        StringJoiner joiner = new StringJoiner(",");
        for (int i = 0; i < person; i++) {
            String name = String.valueOf(i);
            joiner.add(name);
        }
        Order order = new Order();
        String input = joiner.toString();
        assertThrows(CustomIllegalArgumentException.class, () -> order.addWeekdays(input));
        assertThrows(CustomIllegalArgumentException.class, () -> order.addWeekends(input));
    }

    @Test
    @DisplayName("총 근무자가 35명 초과면 에러를 발생한다.")
    void test3() {
        StringJoiner joiner = new StringJoiner(",");
        for (int i = 0; i < 36; i++) {
            String name = String.valueOf(i);
            joiner.add(name);
        }

        Order order = new Order();
        String input = joiner.toString();
        order.addWeekends(input);
        order.addWeekdays(input);

        assertThrows(CustomIllegalArgumentException.class, order::validMaxSize);
    }

}
