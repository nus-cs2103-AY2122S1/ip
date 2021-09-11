package duke;

public class WrongInputException extends DukeException {

    public WrongInputException() {
        super();
    }

    /**
     * Return string message specific for the exception.
     *
     * @return String message.
     */
    @Override
    public String toString() {
        return super.toString() + "I do not know what is this!";
    }
}
