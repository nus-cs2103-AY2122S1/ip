package duke;

public class DukeException extends IllegalArgumentException {

    DukeException() {
        super();
    }

    /**
     * Return string message specific for the exception.
     *
     * @return String message.
     */
    @Override
    public String toString() {
        return "RAWR!! ";
    }
}
