public class DukeException extends Exception {
    private final String message;

    public DukeException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return "OOPS!!! " + this.message;
    }
}
