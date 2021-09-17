package side.exception;

/**
 * WrongFormatException is thrown when user inputs command in a wrong format.
 *
 * @author Lua Yi Da
 */

public class WrongFormatException extends SideException {

    /**
     * Initialises a WrongFormatException.
     */
    public WrongFormatException(String format) {
        super("Follow this format, don't make this worse:\n" + format);
    }
}
