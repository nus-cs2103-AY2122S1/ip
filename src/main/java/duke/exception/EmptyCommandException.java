package duke.exception;

/**
 * An exception that handles command with empty command.
 */
public class EmptyCommandException extends DukeException {

    /**
     * Constructs an EmptyCommandException instance that handles empty command.
     *
     * @param errorMessage The message that prompts user to write some command.
     */
    public EmptyCommandException(String errorMessage) {
        super(errorMessage);
    }
}
