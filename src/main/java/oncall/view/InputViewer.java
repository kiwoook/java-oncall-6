package oncall.view;

import camp.nextstep.edu.missionutils.Console;

public class InputViewer {

    public String startInput() {
        System.out.print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");

        return Console.readLine();
    }

    public String weekdaysInput() {
        System.out.println("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");

        return Console.readLine();
    }

    public String weekendsInput() {
        System.out.println("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");

        return Console.readLine();
    }
}
