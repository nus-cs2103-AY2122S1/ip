package duke.exception;

/**
 * The DukeException is an exception that is thrown when Duke is run.
 *
 * It contains the exceptions that are thrown due to wrong inputs given and is specific to this version of Duke.
 * Names of exception should be self-explanatory in describing when it should be used.
 *
 * @author Benedict Chua
 */
public class InvalidCommandException extends DukeException {
    public InvalidCommandException(String command) {
        super("BAKA! I don't understand this command!\n" +
                String.format("     Command: %s\n", command) +
                "     You might want to check for spelling and potential whitespaces!");
    }
}

