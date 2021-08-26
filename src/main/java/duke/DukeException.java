package duke;

public class DukeException extends Exception {
    private String customMessage;
    public DukeException(String message) {
        super(message);
        this.customMessage = message;
    }

    @Override
    public String toString() {
        return customMessage;
    }
}
