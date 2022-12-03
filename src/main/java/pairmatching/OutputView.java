package pairmatching;

public class OutputView {
    private static final String MENU_LIST_MESSAGE = "기능을 선택하세요.";

    public void printMenuList() {
        System.out.println(MENU_LIST_MESSAGE);
        for (Menu menu : Menu.values()) {
            System.out.println(menu.getMenuMessage());
        }
    }
}
