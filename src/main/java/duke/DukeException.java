package duke;

/**
 * Class that represents exceptions specific to Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructor for a DukeException.
     *
     * @param msg Error message to be displayed to the user.
     */
    public DukeException(String msg) {
        super(msg);
    }

    /**
     * A method which displays the error message to the user.
     */
    public void print() {
        System.out.println(super.getMessage());
    }
}
