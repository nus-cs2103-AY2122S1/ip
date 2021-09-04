package duke.exception;

public class InvalidTaskNumException extends DukeException {

    private static final String ERROR_MSG = "you typed in an invalid input: ";

    public InvalidTaskNumException(String input) {
        super(ERROR_MSG + input);
    }

    public InvalidTaskNumException(int input) {
        super(ERROR_MSG + input);
    }
}
