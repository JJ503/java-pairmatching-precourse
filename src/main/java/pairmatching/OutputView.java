package pairmatching;

import java.util.ArrayList;
import java.util.List;

public class OutputView {
    private static final String MENU_LIST_MESSAGE = "기능을 선택하세요.";
    private static final String DIVIDE_LINE = "#############################################";
    private static final String EXAMPLE_PAIR_SEARCH = "과정, 레벨, 미션을 선택하세요.\nex) 백엔드, 레벨1, 자동차경주";
    private static final String COURSE = "과정: %s\n";
    private static final String MISSION = "미션:";

    public void printMenuList() {
        System.out.println(MENU_LIST_MESSAGE);
        for (Menu menu : Menu.values()) {
            System.out.println(menu.getMenuMessage());
        }
    }

    public void printProgramInformation() {
        System.out.println(System.lineSeparator() + DIVIDE_LINE);
        printCourse();
        printMission();
        System.out.println(DIVIDE_LINE);
        System.out.println(EXAMPLE_PAIR_SEARCH);
    }

    private void printCourse() {
        System.out.printf(COURSE, String.join(" | ", getCourses()));
    }

    private List<String> getCourses() {
        List<String> courses = new ArrayList<>();
        for (Course course : Course.values()) {
            courses.add(course.getName());
        }

        return courses;
    }

    private void printMission() {
        System.out.println(MISSION);
        for (Level level : Level.values()) {
            System.out.println(level.getMissionMessage());
        }
    }
}
