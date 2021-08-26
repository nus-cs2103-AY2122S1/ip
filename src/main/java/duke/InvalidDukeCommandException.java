package duke;

/**
 * Represents an exception thrown due to unknown / unimplemented Duke command.
 */
public class InvalidDukeCommandException extends DukeException {
    public InvalidDukeCommandException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
