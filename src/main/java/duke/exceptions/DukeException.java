package duke.exceptions;

/**
 * Represents exceptions created from Duke bot operations.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    public static DukeException missingInput(String taskName) {
        return new DukeException(String.format("☹ OOPS!!! The description of a %s cannot be empty.", taskName));
    }
}
