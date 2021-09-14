package duke.exception;

public class InvalidIndexException extends DukeException {

    private static final String INVALID_INDEX_MSG = "None of the entries you added are valid!";

    public InvalidIndexException() {
        super(INVALID_INDEX_MSG);
    }
}
