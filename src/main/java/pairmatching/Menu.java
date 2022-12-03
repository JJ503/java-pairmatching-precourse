package pairmatching;

public enum Menu {
    PAIR_MATH("1", "페어 매칭"),
    PAIR_CHECK("2", "페어 조회"),
    PAIR_INITIALIZATION("3", "페어 초기화"),
    END_PROGRAM("Q", "종료");

    private final String option;
    private final String functionType;

    Menu(String option, String functionType) {
        this.option = option;
        this.functionType = functionType;
    }

    public String getMenuMessage() {
        return String.format("%s. %s", option, functionType);
    }
}
