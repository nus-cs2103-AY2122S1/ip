package duke.exception;

public class UnrecognisedCommandException extends DukeException {

    private static final String ERROR_MSG = "you typed in something i cannot recognise!";
    private static final String DID_YOU_MEAN = "did you mean to type ";

    public UnrecognisedCommandException() {
        super(ERROR_MSG);
    }

    public UnrecognisedCommandException(String commandWord) {
        super(ERROR_MSG + "\n" + DID_YOU_MEAN + commandWord + "?");
    }
}
