package abyss.exception;

/**
 * Represents an exception that occurs when user inputs an invalid to-do.
 */
public class InvalidTodoException extends InvalidCommandException {
    /**
     * Constructs an InvalidTodoException.
     */
    public InvalidTodoException() {
        super("Description of a 'todo' task piece cannot be empty.");
    }
}
