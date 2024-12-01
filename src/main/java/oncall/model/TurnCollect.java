package oncall.model;

import static oncall.utils.Constants.MONTH_DAYS;

import java.time.DayOfWeek;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import oncall.exception.CustomIllegalArgumentException;
import oncall.utils.ErrorMessage;
import oncall.utils.StringUtils;

public class TurnCollect {

    private Deque<Name> weekdaysDeque;
    private Deque<Name> weekendsDeque;

    public TurnCollect() {
        this.weekdaysDeque = new ArrayDeque<>();
        this.weekendsDeque = new ArrayDeque<>();
    }

    public void addWeekdays(String input) {
        List<Name> names = Arrays.stream(StringUtils.split(",", input, null)).map(Name::from).toList();

        validDuplicateNames(names);
        validSize(names);

        this.weekdaysDeque = new ArrayDeque<>(names);
    }

    public void addWeekends(String input) {
        List<Name> names = Arrays.stream(StringUtils.split(",", input, null)).map(Name::from).toList();

        validDuplicateNames(names);
        validSize(names);

        this.weekendsDeque = new ArrayDeque<>(names);
    }

    public void validDuplicateNames(List<Name> names) {
        HashSet<Name> nameSet = new HashSet<>(names);

        if (names.size() != nameSet.size()) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_INPUT);
        }
    }

    public void validSize(List<Name> names) {
        if (names.size() < 5 || names.size() > 35) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_INPUT);
        }
    }

    // 날짜와 요일을 입력받음
    public List<Name> order(int month, DayOfWeek dayOfWeek) {
        List<Name> order = new ArrayList<>();

        // 데이오브 윜이 공유 된다...

        for (int day = 1; day <= MONTH_DAYS[month]; day++) {
            // 먼저 휴일인지 확인한다.
            if (DayOfTheWeek.isWeekends(dayOfWeek) || Holiday.isHoliday(month, day)) {
                // 휴일이면 휴일 데큐에서 빼내야한다.
                Name name = weekendsDeque.peekFirst();
                if (checkContinuouslyName(order, name)) {
                    // 연속적이라면 해당 이름을 대기한다.
                    Name waitName = weekendsDeque.pollFirst();
                    // 지금 이름을 빼내고 큐에 큐에 추가하고 다시 뒤에 넣는다
                    Name nextName = weekendsDeque.pollFirst();
                    weekendsDeque.addFirst(waitName);
                    order.add(nextName);
                    weekendsDeque.addLast(nextName);
                    dayOfWeek = dayOfWeek.plus(1);
                    continue;
                }
                // 연속적이지 않다면 빼낸다.
                // 함수 분리해서 처리하자.
                order.add(getNextNameByWeekend());
                dayOfWeek = dayOfWeek.plus(1);
                continue;
            }
            // 휴일이 아니라면
            Name name = weekdaysDeque.peekFirst();
            if (checkContinuouslyName(order, name)) {
                Name waitName = weekdaysDeque.pollFirst();
                Name nextName = weekdaysDeque.pollFirst();
                weekdaysDeque.addFirst(waitName);
                order.add(nextName);
                weekdaysDeque.addLast(nextName);
                dayOfWeek = dayOfWeek.plus(1);
                continue;
            }

            order.add(getNextNameByWeekday());
            dayOfWeek = dayOfWeek.plus(1);
        }

        return order;
    }


    public Name getNextNameByWeekend() {
        Name nextName = weekendsDeque.pollFirst();
        weekendsDeque.addLast(nextName);

        return nextName;
    }

    public Name getNextNameByWeekday() {
        Name nextName = weekdaysDeque.pollFirst();
        weekendsDeque.addLast(nextName);

        return nextName;
    }

    public boolean checkContinuouslyName(List<Name> order, Name name) {
        if (order.isEmpty()) {
            return false;
        }

        int lastIdx = order.size() - 1;
        Name lastOrderName = order.get(lastIdx);

        return lastOrderName.equals(name);
    }


}
