package duke.data.exception;

/**
 * Describes exception caused by an invalid command
 */
public class InvalidCommandException extends DukeException {
    @Override
    public String toString() {
        return "OOPS!!! I'm sorry, but I don't know what that means :(";
    }
}
