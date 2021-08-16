public class DukeException extends Exception {

    public DukeException(String message) {
        super(message);
    }
}

/**
 * Thrown when the user input an unknown string to the chat bot.
 * Chat bot does not recognise the input given.
 */
class UnknownTagException extends DukeException {
    public UnknownTagException() {
        super("No such tag! Please input the correct tag!");
    }
}

/**
 * Thrown when user tries to mark/delete tasks that are not in the list of tasks.
 * User has enter the wrong value.
 */
class NoSuchTaskException extends DukeException {
    public NoSuchTaskException() {
        super("You do not have this many task added yet, please check and try again.");
    }
}

/**
 * Thrown when user did not use the correct format when using the chat bot.
 * Example: deadline without /by, event without /at, etc
 */
class IllegalFormatException extends DukeException {
    public IllegalFormatException() {
        super("Wrong Format used! Please you the correct format!");
    }
}

