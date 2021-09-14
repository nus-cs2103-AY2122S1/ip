package bubbles.exceptions;

/**
 * A class that represents the exception that is thrown
 * when the date of a deadline/event task is invalid/not present.
 */
public class InvalidFormatException extends Exception {
    private String rightFormat;

    /**
     * A public constructor for a InvalidFormatException.
     * @param rightFormat A String representing the supposed right format to be used.
     */
    public InvalidFormatException(String rightFormat) {
        super("");
        this.rightFormat = rightFormat;
    }

    /**
     * Returns the String representation of the Exception.
     *
     * @return The String representation of the Exception.
     */
    @Override
    public String toString() {
        return "☹ What you entered was invalid. ☹ " + this.rightFormat;
    }
}
