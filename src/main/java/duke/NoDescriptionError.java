package duke;

/**
 * Represents the error when there is missing information after a command.
 */
public class NoDescriptionError extends DukeException {
    private String command;

    /**
     * Constructor that takes in the command in String form.
     *
     * @param error which command the user inputted.
     */
    public NoDescriptionError(String error) {
        this.command = error;
    }

    /**
     * Returns a String representation of the error with the corresponding command.
     *
     * @return String representation of the error
     */
    @Override
    public String toString() {
        return super.toString() + "The description of a " + command + " cannot be empty.";
    }
}
