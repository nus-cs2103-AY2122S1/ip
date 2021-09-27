package luke;

public class LukeException extends Exception {

    public static final LukeException INVALID_DATE_FORMAT_EXCEPTION =
            new LukeException("Sorry I can't read that! Enter dates in YYYY-MM-DD format");

    /**
     * Constructor for LukeException.
     */
    LukeException() {
        super();
    }

    /**
     * Constructor for LukeException.
     */
    LukeException(String argument) {
        super(argument);
    }

    /**
     * Overrides Object's toString method.
     *
     * @return String representing Exception.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}