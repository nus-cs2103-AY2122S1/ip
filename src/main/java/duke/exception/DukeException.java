package duke.exception;

/**
 * Signals that an error has occurred during the usage of duke.
 * This abstract class is the general class of exception
 * produced by failed or interrupted operations.
 *
 * @author Zhi Bin
 * @version Duke Level 9
 */
public abstract class DukeException extends Exception {

    /**
     * Returns a formatted message. Used by child
     * class exception to format their error message.
     *
     * @param msg The message to be formatted.
     * @return The formatted error message.
     */
    protected String formatMessage(String msg) {
        String HORIZONTAL_LINE = "____________________________________________________________";
        String INDENTATION = "    ";
        String finalMsg = INDENTATION + HORIZONTAL_LINE + "\n";
        finalMsg += INDENTATION + "☹ OI!!! " + msg + "\n";
        finalMsg += INDENTATION + HORIZONTAL_LINE;
        return finalMsg;
    }
}
