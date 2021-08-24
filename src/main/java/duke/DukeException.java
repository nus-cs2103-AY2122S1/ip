package duke;

public class DukeException extends IllegalArgumentException{

    DukeException() {
        super();
    }

    /**
     * Return string message specific for the exception.
     *
     * @return string message.
     */
    @Override
    public String toString() {
        return "Oops! ";
    }
}
