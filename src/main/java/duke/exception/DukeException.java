package duke.exception;

/**
 * Signals that an error has occurred during the usage of duke.
 * This abstract class is the general class of exception
 * produced by failed or interrupted operations.
 *
 * @author Zhi Bin
 * @version Duke Level 10
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
//        String line = "____________________________________________________________";
//        String indentation = "    ";
//        String finalMsg = indentation + line + "\n";
//        finalMsg += indentation + "OI!!! " + msg + "\n";
//        finalMsg += indentation + line;
//        return finalMsg;
        String indentation = "    ";
        return String.format("%s%s", indentation, msg);
    }
}
