package pairmatching;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PairMatchingController {
    private static final int CATEGORY_SIZE = 3;
    private static final String RESPONSE_YES_REMATCH = "네";
    private static final String RESPONSE_NO_REMATCH = "아니오";

    OutputView outputView = new OutputView();
    InputView inputView = new InputView();
    PairMatching pairMatching;

    public void startProgram() {
        HashMap<Course, CrewsByCourse> crewsByCourses = initCrewList();
        pairMatching = new PairMatching();
        boolean programState = true;

        while (programState) {
            try {
                programState = selectOptionFunction(crewsByCourses);
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private boolean selectOptionFunction(HashMap<Course, CrewsByCourse> crewsByCourses) {
        Menu menu = selectOption();

        if (menu == Menu.PAIR_MATH) {
            List<String> categories = selectCategory();
            pairMatch(categories, crewsByCourses);
        }
        if (menu == Menu.PAIR_CHECK) {
            List<String> categories = selectCategory();
            outputView.printPairMatchResult(pairMatching.getPairMatchResult(categories));
        }
        if (menu == Menu.PAIR_INITIALIZATION) {
            pairMatching.initPairMatch();
            outputView.printInitPairMatchData();
        }

        return menu != Menu.END_PROGRAM;
    }

    private void pairMatch(List<String> categories, HashMap<Course, CrewsByCourse> crewsByCourses) {
        Course course = Course.getMatchCourse(categories.get(0));

        if (pairMatching.isAlreadyMatch(categories) && !askRematchPair()) {
            return;
        }

        outputView.printPairMatchResult(pairMatching.generatePairMatch(categories, crewsByCourses.get(course)));
    }

    private boolean askRematchPair() {
        String response = inputView.readAskRematchPair();
        if (response.equals(RESPONSE_YES_REMATCH)) {
            return true;
        }
        if (response.equals(RESPONSE_NO_REMATCH)) {
            return false;
        }

        throw new IllegalArgumentException();
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

    private Menu selectOption() {
        outputView.printMenuList();
        String inputValue = inputView.readOption();
        validateOption(inputValue);
        return Menu.getMatchMenu(inputValue);
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

    private List<String> selectCategory() {
        outputView.printProgramInformation();
        String category = inputView.readCategory().replace(" ", "");
        List<String> categories = Arrays.asList(category.split(","));
        validateCategory(categories);

        return categories;
    }

    private void validateCategory(List<String> categories) {
        if (isEmpty(categories)) {
            ExceptionMessage.NONE_INPUT.throwException();
        }
        if (isNotThreeCategory(categories)) {
            ExceptionMessage.NONE_INPUT_THREE_CATEGORY.throwException();
        }

        validateCategories(categories);
    }

    private void validateCategories(List<String> categories) {
        String course = categories.get(0);
        String level = categories.get(1);
        String mission = categories.get(2);
        if (isNotExistCourse(course)) {
            ExceptionMessage.INPUT_WRONG_COURSE.throwException();
        }
        if (isNotExistLevel(level)) {
            ExceptionMessage.INPUT_WRONG_LEVEL.throwException();
        }
        if (isNotExistMission(level, mission)) {
            ExceptionMessage.INPUT_WRONG_MISSION.throwException();
        }
    }

    private boolean isEmpty(List<String> categories) {
        return categories.get(0).isEmpty();
    }

    private boolean isNotThreeCategory(List<String> categories) {
        return categories.size() != CATEGORY_SIZE;
    }

    private boolean isNotExistCourse(String course) {
        return Arrays.stream(Course.values()).noneMatch(menu -> menu.isMatchCourse(course));
    }

    private boolean isNotExistLevel(String level) {
        return Arrays.stream(Level.values()).noneMatch(menu -> menu.isMatchLevel(level));
    }

    private boolean isNotExistMission(String level, String mission) {
        return Arrays.stream(Level.values()).noneMatch(menu -> menu.isMatchLevel(level) && menu.isMatchMission(mission));
    }
}
