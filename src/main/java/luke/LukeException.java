package luke;

public class LukeException extends Exception {

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