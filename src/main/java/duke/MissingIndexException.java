package duke;

public class MissingIndexException extends DukeException {

    MissingIndexException() {
        super();
    }

    /**
     * Return string message specific for the exception.
     *
     * @return String message.
     */
    @Override
    public String toString() {
        return super.toString() + "Can't have missing index!";
    }
}
