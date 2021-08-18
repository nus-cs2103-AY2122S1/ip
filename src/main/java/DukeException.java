/**
 * This class represents exceptions created specific to the Duke bot.
 */
public class DukeException extends Exception {
    /***
     * Constructor to create a Duke exception.
     *
     * @param msg The error message of the exception.
     */
    public DukeException(String msg) {
        super(msg);
    }
}