package oncall.controller;

import java.time.DayOfWeek;
import java.util.List;
import oncall.exception.CustomIllegalArgumentException;
import oncall.model.Name;
import oncall.model.StartInput;
import oncall.model.Order;
import oncall.utils.RecoveryUtils;
import oncall.view.InputViewer;
import oncall.view.OutputViewer;

public class OncallController {

    private final InputViewer inputViewer;
    private final OutputViewer outputViewer;
    private final Order order = new Order();

    public OncallController(InputViewer inputViewer, OutputViewer outputViewer) {
        this.inputViewer = inputViewer;
        this.outputViewer = outputViewer;
    }

    public void run() {
        StartInput startInput = getStartInput();
        getWorkers();
        process(startInput);
    }

    public StartInput getStartInput() {
        return RecoveryUtils.executeWithRetry(inputViewer::startInput, StartInput::of);
    }

    public void getWorkers() {
        getWeekdays();
        getWeekends();
    }

    public void getWeekdays() {
        RecoveryUtils.executeWithRetry(inputViewer::weekdaysInput, order::addWeekdays);
    }

    public void getWeekends() {
        try {

            String input = RecoveryUtils.executeWithRetry(inputViewer::weekendsInput);
            order.addWeekends(input);
        } catch (CustomIllegalArgumentException e) {
            outputViewer.printError(e);
            getWorkers();
        }
    }

    public void process(StartInput startInput) {
        int month = startInput.month()
                .getValue();
        DayOfWeek dayOfWeek = startInput.dayOfTheWeek()
                .getDayOfWeek();

        List<String> nameList = order.getOrderNameByMonthAndDayOfWeek(month, dayOfWeek)
                .stream()
                .map(Name::value)
                .toList();

        outputViewer.printResult(month, dayOfWeek, nameList);
    }


}
