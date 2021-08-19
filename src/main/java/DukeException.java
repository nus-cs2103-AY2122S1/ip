public class DukeException extends RuntimeException {
    public enum TYPE {INCOMPLETE, OUT_OF_BOUND}
    protected final TYPE type;
    public DukeException(String message, TYPE type) {
        super(message);
        this.type = type;
    }
}

