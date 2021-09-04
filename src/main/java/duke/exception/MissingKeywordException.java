package duke.exception;

public class MissingKeywordException extends DukeException {

    private static final String ERROR_MSG_FRONT = "you are missing the ";
    private static final String ERROR_MSG_BACK = " keyword!";

    public MissingKeywordException(String keyword) {
        super(ERROR_MSG_FRONT + keyword + ERROR_MSG_BACK);
    }
}
