package oncall.controller;

import oncall.model.DayOfTheWeek;
import oncall.model.Month;
import oncall.model.StartInput;
import oncall.utils.RecoveryUtils;
import oncall.utils.StringUtils;
import oncall.view.InputViewer;
import oncall.view.OutputViewer;

public class OncallController {

    private final InputViewer inputViewer;
    private final OutputViewer outputViewer;

    public OncallController(InputViewer inputViewer, OutputViewer outputViewer) {
        this.inputViewer = inputViewer;
        this.outputViewer = outputViewer;
    }

    // 비상 근무 월과 시작 요일 입력
    public void startInput() {

        StartInput startInput = RecoveryUtils.executeWithRetry(inputViewer::startInput, this::getStartInput);


    }


    public StartInput getStartInput(String input) {
        String s = inputViewer.startInput();
        String[] split = StringUtils.split(",", s, 2);

        Month month = Month.of(split[0]);
        DayOfTheWeek dayOfTheWeek = DayOfTheWeek.of(split[1]);

        return new StartInput(month, dayOfTheWeek);
    }

}
