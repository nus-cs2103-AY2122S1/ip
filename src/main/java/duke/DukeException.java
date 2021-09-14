package duke;

/**
 * A Parent class for Exceptions thrown in Duke.
 */
public class DukeException extends Exception {

    /**
     * A public constructor for Duke Exception.
     * @param str The error message that is passed from the main code.
     */
    public DukeException(String str) {
        // calling the constructor of parent Exception
        super(str);
    }


    /**
     * Returns the string representation of Duke Exception.
     * @return the string representation of Duke Exception.
     */
    @Override
    public String toString() {
        return getMessage();
    }
}
