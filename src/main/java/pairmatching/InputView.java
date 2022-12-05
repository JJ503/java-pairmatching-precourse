package pairmatching;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String ASK_REMATCH = "매칭 정보가 있습니다. 다시 매칭하시겠습니까?\n네 | 아니오";

    public String readOption() {
        return Console.readLine();
    }

    public String readCategory() {
        return Console.readLine();
    }

    public String readAskRematchPair() {
        System.out.println(ASK_REMATCH);
        return Console.readLine();
    }
}
