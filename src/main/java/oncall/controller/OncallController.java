package oncall.controller;

import java.time.DayOfWeek;
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
    private TurnCollect turnCollect = new TurnCollect();

    public OncallController(InputViewer inputViewer, OutputViewer outputViewer) {
        this.inputViewer = inputViewer;
        this.outputViewer = outputViewer;
    }

    // 비상 근무 월과 시작 요일 입력
    public void run() {
        StartInput startInput = RecoveryUtils.executeWithRetry(inputViewer::startInput, this::getStartInput);
        getWeekDays();
        getWeekends();

    }


    public StartInput getStartInput(String input) {
        String s = inputViewer.startInput();
        String[] split = StringUtils.split(",", s, 2);

        Month month = Month.of(split[0]);
        DayOfTheWeek dayOfTheWeek = DayOfTheWeek.of(split[1]);

        return new StartInput(month, dayOfTheWeek);
    }

    public void getWeekDays() {
        RecoveryUtils.executeWithRetry(inputViewer::weekdaysInput, turnCollect::addWeekdays);
    }

    public void getWeekends() {
        RecoveryUtils.executeWithRetry(inputViewer::weekdaysInput, turnCollect::addWeekends);
    }


    public void process(StartInput startInput) {
        int month = startInput.month()
                .getValue();
        DayOfWeek dayOfWeek = startInput.dayOfTheWeek()
                .getDayOfWeek();

        List<Name> nameList = turnCollect.order(month, dayOfWeek);


    }



}
