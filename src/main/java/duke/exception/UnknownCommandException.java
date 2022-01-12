package duke.exception;

/**
 * The UnknownCommandException class encapsulates information
 * and methods pertaining to exceptions relating to unknown commands in Duke.
 *
 * @author Frederick Pek
 * @version CS2103 AY21/22 Sem 1 iP
 */
public class UnknownCommandException extends DukeException {
    /**
     * Creates and initalizes a new UnknownCommandException with the task.
     *
     * @param command The command that failed to be parsed successfully.
     * @return A new UnknownCommandException object.
     */
    public UnknownCommandException(String command) {
        super(String.format("The command, \"%s\", is unknown.", command));
    }
}