package duke;

public class DukeException extends Exception {

    /**
     * Constructor for DukeException.
     */
    DukeException() {
        super();
    }

    /**
     * Constructor for DukeException.
     */
    DukeException(String argument) {
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
