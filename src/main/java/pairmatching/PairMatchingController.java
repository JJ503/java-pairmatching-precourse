package pairmatching;

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
}
