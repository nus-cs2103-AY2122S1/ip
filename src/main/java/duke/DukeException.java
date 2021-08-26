package duke;

/**
 * This class contains the exceptions for the Duke bot.
 */
public class DukeException extends RuntimeException{
    private static final String preMessage = "Sorry, ";
    public DukeException(String errorMessage) {
        super(preMessage + errorMessage);
    }
}
