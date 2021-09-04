package duke.exception;

public class MissingArgumentException extends DukeException {

    private static final String ERROR_MSG = "you're missing an argument for this command!";

    public MissingArgumentException() {
        super(ERROR_MSG);
    }
}
