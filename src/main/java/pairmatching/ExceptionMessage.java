package pairmatching;

public enum ExceptionMessage {
    NONE_INPUT("값을 입력해 주시길 바랍니다."),
    INPUT_WRONG_FUNCTION_TYPE("기능 목록 중 하나를 선택해 주시길 바랍니다 (1, 2, 3, Q 중 선택).");

    private final String exceptionMessage;

    ExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public void throwException() {
        throw new IllegalArgumentException(exceptionMessage);
    }
}
