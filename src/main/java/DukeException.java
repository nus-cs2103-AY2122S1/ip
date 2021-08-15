/**
 * This is the parent class for all Duke exceptions.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super("☹ OOPS!!! " + message);
    }
}

/**
 * Thrown when the user inputs a todo command without a description.
 */
class EmptyTodoDescriptionException extends DukeException {
    public EmptyTodoDescriptionException() {
        super("The description of a todo cannot be empty.");
    }
}

/**
 * Thrown when the user inputs an unknown command.
 */
class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
        super("I'm sorry, but I don't know what that means :-(");
    }
}

/**
 * Thrown when the user inputs a command in an invalid format.
 */
class InvalidFormatException extends DukeException {
    public InvalidFormatException(String format) {
        super("Please follow this format: " + format);
    }
}

/**
 * Thrown when the user tries to access an invalid task index.
 */
class InvalidTaskIndexException extends DukeException {
    public InvalidTaskIndexException() {
        super("Invalid task index!");
    }
}