package side.exception;

/**
 * SideException encapsulates the errors specific to Side when it encounters runtime errors.
 *
 * It contains the exceptions that are thrown due to wrong inputs given.
 *
 * @author Lua Yi Da
 */

public class SideException extends RuntimeException {

    private String message;

    /**
     * Initialises a SideException.
     *
     * @param message String to be displayed.
     */
    public SideException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
