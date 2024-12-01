package oncall.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DayOfTheWeekTest {

    @Test
    @DisplayName("요일을 반환하는 메서드")
    void test1() {
        List<String> expectList = List.of("월", "화", "수", "목", "금", "토", "일");
        DayOfWeek startDayOfWeek = DayOfWeek.MONDAY;

        for (int i = 0; i < 31; i++) {
            DayOfWeek dayOfWeek = startDayOfWeek.plus(i);

            String result = DayOfTheWeek.toDayOfWeek(dayOfWeek);

            assertThat(result).isEqualTo(expectList.get(i % 7));
        }
    }

}
