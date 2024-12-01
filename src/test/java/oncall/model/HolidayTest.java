package oncall.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class HolidayTest {


    @ParameterizedTest
    @DisplayName("휴일이면 트루를 반환해야한다.")
    @CsvSource(value = {"1:1", "3:1", "5:5", "6:6", "8:15", "10:3", "10:9", "12:25"}, delimiter = ':')
    void test1(String month, String day) {

        int parseMonth = Integer.parseInt(month);
        int parseDay = Integer.parseInt(day);

        assertThat(Holiday.isHoliday(parseMonth, parseDay)).isTrue();
    }
}
