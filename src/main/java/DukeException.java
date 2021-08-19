public class DukeException extends RuntimeException {
    public enum exceptionType {INCOMPLETE, INVALID, OUT_OF_BOUND};

    public final exceptionType type;
    public DukeException(String message, exceptionType type) {
        super(message);
        this.type = type;
    }
}
