package duke.exception;

public class NoKeywordException extends DukeException {

    private static final String NO_KEYWORD_MSG = "Please specify a date using the ";
    private static final String NO_KEYWORD_MSG_BACK = " keyword.";

    public NoKeywordException(String keyword) {
        super(NO_KEYWORD_MSG + keyword + NO_KEYWORD_MSG_BACK);
    }
}
