package duke;

/**
 * Exception meant for handling those specific to Duke.
 *
 * @author Wong Yun Rui Chris
 */
public class DukeException extends Exception {
    /**
     * A public constructor for initialising DukeException.
     *
     * @param message The message meant for the Duke Exception
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Returns the String representation for this DukeException formatted to be printed by Duke.
     *
     * @return The String representation of this DukeException
     */
    @Override
    public String toString() {
        return ":( OOPS!! " + super.getMessage();
    }
}
