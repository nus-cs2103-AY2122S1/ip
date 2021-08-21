package duke;

public class DukeException extends Exception {
    private final String errorMessage;

    public DukeException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "\uD83D\uDE41 OOPS!!! " + this.errorMessage;
    }
}
