package duke;

/**
 * DukeException is an Exception to be thrown when functionalities of the chat bot fails.
 *
 * @author leezhixuan
 */
public class DukeException extends Exception{

    /**
     * Creates an instance of DukeException.
     *
     * @param message Message that informs users about their errors.
     */
    public DukeException(String message) {
        super(message);
    }
}
