package duke.exception;

public class NoDescriptionException extends DukeException {

    private static final String NO_DESCRIPTION_MSG = "The description of a ";
    private static final String NO_DESCRIPTION_MSG_BACK = " cannot be empty.";

    public NoDescriptionException(String task) {
        super(NO_DESCRIPTION_MSG + task + NO_DESCRIPTION_MSG_BACK);
    }
}
