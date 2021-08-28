package duke;

/**
 * This class encapsulates the exceptions that Duke throws out when something goes wrong.
 */
public class DukeExceptionBase extends Exception {

    private String exceptionMsg;

    /**
     * Constructor to create a new Duke Exception.
     * @param msg The message that Duke will print due to this exception.
     */
    public DukeExceptionBase(String msg) {
        this.exceptionMsg = msg;
    }

    /**
     * Ask Duke to print out this exception's error message.
     */
    public void dukeSayErrorMsg() {
        Duke.dukeSays(this.exceptionMsg);
    }

    /**
     * The exception's toString method to print out the exception message.
     * @return the exception message for the current exception object.
     */
    @Override
    public String toString() {
        return exceptionMsg;
    }

}
