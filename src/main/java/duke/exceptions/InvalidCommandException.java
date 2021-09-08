package duke.exceptions;

/** Throws error for invalid commands, e.g. task */
public class InvalidCommandException extends DukeException {
    public InvalidCommandException(String message) {
        super(message);
    }
}