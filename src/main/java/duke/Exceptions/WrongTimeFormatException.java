package duke.Exceptions;

/**
 * Represents WrongTimeFormatException class
 */
public class WrongTimeFormatException extends Exception {

    /**
     * The Constructor for WrongTimeFormatException
     * @param message
     */
    public WrongTimeFormatException(String message) {
        super(String.format("☹ OOPS!!! The input time format " + message + " is wrong, please enter in format yyyy-MM-dd HH:mm"));
    }
}
