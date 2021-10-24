package duke.exception;

/**
 * Thrown to indicate that the command is not recognised by the bot.
 */
public class UnrecognisedCommandException extends DukeException {

    /**
     * Constructs an UnrecognisedCommandException with a detail message.
     */
    public UnrecognisedCommandException() {
        super("This command is not recognised! Please enter a valid command!");
    }
}
