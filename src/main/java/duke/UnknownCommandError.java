package duke;

/**
 * Represents the error when the user inputs an unknown command
 */
public class UnknownCommandError extends DukeException {
    /**
     * Returns a String representation of the error.
     *
     * @return String representation of the error
     */
    @Override
    public String toString() {
        return super.toString() + "I'm sorry, but I don't know what that means :-(";
    }
}
