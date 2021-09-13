package duke;

public class InvalidIndexException extends DukeException {

    private static final String INVALID_INDEX_MSG = "This entry does not exist!";

    public InvalidIndexException() {
        super(INVALID_INDEX_MSG);
    }
}
