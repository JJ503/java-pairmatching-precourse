package pairmatching;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PairMatchingController {

    OutputView outputView = new OutputView();
    InputView inputView = new InputView();

    public void startProgram() {
        HashMap<Course, CrewsByCourse> crewsByCourses = initCrewList();
        selectOption();
    }

    private HashMap<Course, CrewsByCourse> initCrewList() {
        HashMap<Course, CrewsByCourse> crewsByCourses = new HashMap<>();
        for (CrewNamesFile crewFile : CrewNamesFile.values()) {
            List<String> crewNames = getNameList(crewFile.getFilePath());
            crewsByCourses.put(crewFile.getCourse(), new CrewsByCourse(crewFile.getCourse(), crewNames));
        }

        return crewsByCourses;
    }

    private List<String> getNameList(String path) {
        EnterCrewFile enterCrewFile = new EnterCrewFile();
        try {
            return enterCrewFile.enterFileToList(path);
        } catch (IOException exception) {
            System.out.println(exception);
        }

        return null;
    }

    private void selectOption() {
        outputView.printMenuList();
        String inputValue = inputView.readOption();
        validateOption(inputValue);
    }

    private void validateOption(String option) {
        if (isEmpty(option)) {
            ExceptionMessage.NONE_INPUT.throwException();
        }
        if (isNotExistOption(option)) {
            ExceptionMessage.INPUT_WRONG_FUNCTION_TYPE.throwException();
        }
    }

    private boolean isEmpty(String validateInput) {
        return validateInput.isEmpty();
    }

    private boolean isNotExistOption(String option) {
        return Arrays.stream(Menu.values()).noneMatch(menu -> menu.isMatchOption(option));
    }
}
