package duke.exceptions;

/**
 * Encapsulates the EmptyDescriptionException thrown by the Duke bot.
 */
public class EmptyDescriptionException extends DukeException {
    /**
     * Constructor for CorruptedFileException.
     */
    public EmptyDescriptionException() {
        super("Sorry! Your task description cannot be empty!");
    }

}
