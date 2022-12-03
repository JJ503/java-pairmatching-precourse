package pairmatching;

import java.util.Arrays;

public class PairMatchingController {
    OutputView outputView = new OutputView();
    InputView inputView = new InputView();

    public void startProgram() {
        selectOption();
    }

    private void selectOption() {
        outputView.printMenuList();
        String inputValue = inputView.readOption();
        System.out.println(inputValue);
    }

    private void validateOption(String option) {
        if (isEmpty(option)) {
            ExceptionMessage.NONE_INPUT.throwException();
        }
        if (isExistOption(option)) {
            ExceptionMessage.INPUT_WRONG_FUNCTION_TYPE.throwException();
        }
    }

    private boolean isEmpty(String validateInput) {
        return validateInput.isEmpty();
    }

    private boolean isExistOption(String option) {
        return Arrays.stream(Menu.values()).anyMatch(menu -> menu.isMatchOption(option));
    }
}
