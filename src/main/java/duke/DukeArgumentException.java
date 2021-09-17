package duke;

/**
 * Exception that is thrown when arguments passed into Duke commands are incorrect.
 */
public class DukeArgumentException extends IllegalArgumentException {
    public DukeArgumentException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return "@OOPS!!! " + super.getMessage();
    }
}
