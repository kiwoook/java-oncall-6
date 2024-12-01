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

public class Order {

    private Deque<Name> weekdaysOrder;
    private Deque<Name> weekendsOder;

    public Order() {
        this.weekdaysOrder = new ArrayDeque<>();
        this.weekendsOder = new ArrayDeque<>();
    }

    public void addWeekdays(String input) {
        List<Name> names = Arrays.stream(StringUtils.split(",", input, null))
                .map(Name::from)
                .toList();

        validDuplicateNames(names);
        validSize(names);

        this.weekdaysOrder = new ArrayDeque<>(names);
    }

    public void addWeekends(String input) {
        List<Name> names = Arrays.stream(StringUtils.split(",", input, null))
                .map(Name::from)
                .toList();

        validDuplicateNames(names);
        validSize(names);

        this.weekendsOder = new ArrayDeque<>(names);
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

    public List<Name> getOrderNameByMonthAndDayOfWeek(int month, DayOfWeek startDayofWeek) {
        List<Name> order = new ArrayList<>();

        for (int day = 1; day <= MONTH_DAYS[month]; day++) {
            checkWeek(month, day, startDayofWeek, order);
        }

        return order;
    }

    private void checkWeek(int month, int day, DayOfWeek startDayofWeek, List<Name> order) {
        DayOfWeek dayOfWeek = startDayofWeek.plus(day - 1);

        if (DayOfTheWeek.isWeekends(dayOfWeek) || Holiday.isHoliday(month, day)) {
            addName(order, weekendsOder);
            return;
        }

        addName(order, weekdaysOrder);
    }

    private void addName(List<Name> order, Deque<Name> deque) {
        Name name = deque.peekFirst();
        if (checkContinuouslyName(order, name)) {
            Name waitName = deque.pollFirst();
            Name nextName = deque.pollFirst();
            deque.addFirst(waitName);
            order.add(nextName);
            deque.addLast(nextName);
            return;
        }

        order.add(getNextName(deque));
    }


    public Name getNextName(Deque<Name> deque) {
        Name nextName = deque.pollFirst();
        deque.addLast(nextName);

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
