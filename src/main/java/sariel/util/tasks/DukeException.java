package sariel.util.tasks;

/**
 * DukeException, an exception that arose from inaccurate input.
 *
 */
public class DukeException extends Exception {

    /**
     * Default DukeException constructor.
     */
    public DukeException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * DukeException constructor with input message.
     *
     * @param message The message the DukeException carries.
     */
    public DukeException(String message) {
        super(message);
    }


}
