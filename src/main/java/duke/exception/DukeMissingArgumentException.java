package duke.exception;

public class DukeMissingArgumentException extends DukeException {
    public DukeMissingArgumentException(String type) {
        super(String.format("Error: '%s' argument is missing!", type));
    }
}
