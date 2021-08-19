/**
 * A class that is an exception to be thrown for errors relating to the Duke.
 */

public class DukeException extends Exception {

    /**
     * Constructor for the DukeException.
     * @param message the message to be printed.
     */
    public DukeException(String message) {
        super(message);
    }


    /**
     * the toString method of the DukeException.
     * @return a String
     */
    @Override
    public String toString() {
        return this.getMessage();
    }

}
