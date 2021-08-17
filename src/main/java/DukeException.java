public class DukeException extends RuntimeException {
    private final String message;
    public DukeException(String message) {
        super(message);
        this.message = message;
    }
    @Override
    public String toString() {
        return this.message;
    }
}
