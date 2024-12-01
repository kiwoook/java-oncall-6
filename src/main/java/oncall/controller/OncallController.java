package oncall.controller;

import java.time.DayOfWeek;
import java.util.List;
import oncall.model.Name;
import oncall.model.StartInput;
import oncall.model.TurnCollect;
import oncall.utils.RecoveryUtils;
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
        StartInput startInput = getStartInput();
        getWeekDays();
        getWeekends();
        process(startInput);
    }

    public StartInput getStartInput() {
        return RecoveryUtils.executeWithRetry(inputViewer::startInput, StartInput::of);
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
