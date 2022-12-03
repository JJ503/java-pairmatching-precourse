package pairmatching;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PairMatchingController {
    OutputView outputView = new OutputView();
    InputView inputView = new InputView();

    public void startProgram() {
        List<CrewsByCourse> crewsByCourses = initCrewList();
        selectOption();
    }

    private List<CrewsByCourse> initCrewList() {
        List<CrewsByCourse> crewsByCourses = new ArrayList<>();
        for (CrewNamesFile crewFile : CrewNamesFile.values()) {
            List<String> crewNames = getNameList(crewFile.getFilePath());
            List<Crew> crews = setCrewList(crewNames, crewFile.getCourse());
            crewsByCourses.add(new CrewsByCourse(crewFile.getCourse(), crews));
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

    private List<Crew> setCrewList(List<String> crewNames, Course course) {
        List<Crew> crews = new ArrayList<>();

        for (String name : crewNames) {
            crews.add(new Crew(course, name));
        }

        return crews;
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
