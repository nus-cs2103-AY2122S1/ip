package duke.exception;

public class DukeMissingIndexException extends DukeException {
    public DukeMissingIndexException(String taskType) {
        super(String.format("OOPS!!! The index argument for '%s' cannot be empty. x_x", taskType));
    }
}