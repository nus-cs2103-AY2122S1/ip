package duke;

/**
 * Class for duke.Duke-specific exceptions.
 * @author Liew Jian Hong
 */

public class DukeException extends Exception {
    /**
     * Constructor for duke.DukeException.
     * @param message Exception Message.
     */
    public DukeException(String message) {
        super(message);
    }
}
