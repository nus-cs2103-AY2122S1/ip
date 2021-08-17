public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Error detected by Duke:\n"
                + getMessage();
    }
}
