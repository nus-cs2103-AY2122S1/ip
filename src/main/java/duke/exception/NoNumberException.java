package duke.exception;

public class NoNumberException extends DukeException {

    private static final String NO_NUMBER_MSG = "Please enter a number after ";

    public NoNumberException(String task) {
        super(NO_NUMBER_MSG + task + "!");
    }
}
