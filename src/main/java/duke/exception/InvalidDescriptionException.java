package duke.exception;

public class InvalidDescriptionException extends DukeException {
    private final String error;

    public InvalidDescriptionException(String msg, String task) {
        super(msg);
        this.error = String.format("OOPS!!! Please enter a proper description for %s", task);
    }

    @Override
    public String getError() {
        return this.error;
    }
}
