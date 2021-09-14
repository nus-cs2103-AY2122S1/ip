package duke;
/**
 * An exception thrown in Duke when
 * the format of the user's input is invalid.
 */
public class InvalidFormatException extends Exception{

    /**
     * A public constructor for Invalid Format Exception.
     * @param str The error message that is passed from the main code.
     */
    public InvalidFormatException(String str) {
        super(str);
    }

    /**
     * Returns the string representation of Invalid Format Exception.
     * @return the string representation of Invalid Format Exception.
     */
    @Override
    public String toString() {
        return getMessage() ;
    }
}
