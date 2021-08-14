/**
 * Exception meant for handling those specific to Duke
 *
 * @author Wong Yun Rui Chris
 */
public class DukeException extends Exception {
    /**
     * A public constructor for initialising DukeException
     *
     * @param message The message meant for the Duke Exception
     */
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return ":( OOPS!! " + super.getMessage();
    }
}
