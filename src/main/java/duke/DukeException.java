package duke;

/**Class that handles the exceptions*/
public abstract class DukeException extends RuntimeException {

    /** Constructor for DukeException class */
    public DukeException(String message) {
        super(message);
    }

    public String getErrorMessage() {
        return "";
    }
}

/** Class that handles the null task input exceptions */
class NullTaskError extends DukeException {

    /** Constructor for NullTaskError class */
    public NullTaskError() {
        super("OOPS!!! The description of a todo cannot be empty.");
    }

    /**
     * Returns the error message when the user does not enter
     * the description of a particular task
     *
     * @param type The type of the task - to-do or deadline or event
     * @return The corresponding output error message
     */
    public String getErrorMessage(String type) {
        return "OOPS!!! The description of a " + type + " cannot be empty.";
    }
}

/**Class that handles the non exist keyword exceptions*/
class NonExistentKeyword extends DukeException {

    /** Constructor for NonExistentKeyword class */
    public NonExistentKeyword() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Returns an error message when the user types in a command
     * that is not supported
     *
     * @return The error message as well as a list of approved commands
     * that they can enter
     */
    public String getErrorMessage() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-( " + "\n" +
                "Type 'help' to see the list of commands available!";
    }
}
