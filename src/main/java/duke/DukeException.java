package duke;

public class DukeException extends Exception {
    private String errorMessage;

    public DukeException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "\uD83D\uDE41 OOPS!!! " + this.errorMessage;
    }
}
