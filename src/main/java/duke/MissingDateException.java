package duke;

public class MissingDateException extends DukeException {

    MissingDateException() {
        super();
    }

    /**
     * Return string message specific for the exception.
     *
     * @return String message.
     */
    @Override
    public String toString() {
        return super.toString() + "Can't have missing date for task type!";
    }
}
