package side.exception;

/**
 * WrongDatetimeException is thrown when user attempts to do enter an invalid datetime format.
 *
 * @author Lua Yi Da
 */

public class WrongDatetimeException extends SideException {

    /**
     * Initialises a WrongDateTimeException.
     */
    public WrongDatetimeException() {
        super("Wrong datetime format...\n" + "Use [YYYY-MM-DD] or [YYYY-MM-DD], [HHMM]");
    }
}
