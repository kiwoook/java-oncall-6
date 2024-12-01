package oncall;

import oncall.controller.OncallController;
import oncall.view.InputViewer;
import oncall.view.OutputViewer;

public class Application {
    public static void main(String[] args) {
        InputViewer inputViewer = new InputViewer();
        OutputViewer outputViewer = new OutputViewer();
        OncallController oncallController = new OncallController(inputViewer, outputViewer);

        oncallController.run();
    }
}
