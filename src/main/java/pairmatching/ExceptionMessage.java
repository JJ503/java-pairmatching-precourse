package pairmatching;

public enum ExceptionMessage {
    NONE_INPUT("값을 입력해 주시길 바랍니다."),
    INPUT_WRONG_FUNCTION_TYPE("기능 목록 중 하나를 선택해 주시길 바랍니다 (1, 2, 3, Q 중 선택)."),
    NONE_INPUT_THREE_CATEGORY("과정, 레벨, 미션을 모두 입력해주시길 바랍니다."),
    INPUT_WRONG_COURSE("해당 과정을 찾지 못했습니다."),
    INPUT_WRONG_LEVEL("해당 레벨을 찾지 못했습니다."),
    INPUT_WRONG_MISSION("해당 미션을 찾지 못했습니다."),
    PAIR_MATCHING_IS_NOT_POSSIBLE("페어 매칭을 할 수 없습니다.");

    private final String exceptionMessage;

    ExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public void throwException() {
        throw new IllegalArgumentException(exceptionMessage);
    }
}
