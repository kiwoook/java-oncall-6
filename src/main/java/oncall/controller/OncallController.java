package oncall.controller;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;
import oncall.model.DayOfTheWeek;
import oncall.model.Month;
import oncall.model.Name;
import oncall.model.StartInput;
import oncall.model.TurnCollect;
import oncall.utils.RecoveryUtils;
import oncall.utils.StringUtils;
import oncall.view.InputViewer;
import oncall.view.OutputViewer;

public class OncallController {

    private final InputViewer inputViewer;
    private final OutputViewer outputViewer;
    private final TurnCollect turnCollect = new TurnCollect();

    public OncallController(InputViewer inputViewer, OutputViewer outputViewer) {
        this.inputViewer = inputViewer;
        this.outputViewer = outputViewer;
    }

    public void run() {
        StartInput startInput = RecoveryUtils.executeWithRetry(inputViewer::startInput, this::getStartInput);
        getWeekDays();
        getWeekends();
        process(startInput);
    }


    public StartInput getStartInput(String input) {
        String[] split = StringUtils.split(",", input, 2);

        System.out.println(Arrays.toString(split));
        Month month = Month.of(split[0]);
        DayOfTheWeek dayOfTheWeek = DayOfTheWeek.from(split[1]);
        return new StartInput(month, dayOfTheWeek);
    }

    public void getWeekDays() {
        RecoveryUtils.executeWithRetry(inputViewer::weekdaysInput, turnCollect::addWeekdays);
    }

    public void getWeekends() {
        RecoveryUtils.executeWithRetry(inputViewer::weekendsInput, turnCollect::addWeekends);
    }


    public void process(StartInput startInput) {
        int month = startInput.month()
                .getValue();
        DayOfWeek dayOfWeek = startInput.dayOfTheWeek()
                .getDayOfWeek();

        List<String> nameList = turnCollect.order(month, dayOfWeek)
                .stream()
                .map(Name::getValue)
                .toList();

        outputViewer.printResult(month, dayOfWeek, nameList);
    }


}
