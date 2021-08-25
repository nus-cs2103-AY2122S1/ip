package duke.exception;

public class DukeMissingDescriptionException extends DukeException {
    public DukeMissingDescriptionException(String commandType) {
        super(String.format("OOPS!!! The description of %s cannot be empty! x_x", commandType));
    }
}