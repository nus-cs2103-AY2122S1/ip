package duke;

public class MissingKeywordException extends DukeException {
    public MissingKeywordException() {
        super();
    }

    /**
     * Return string message specific for the exception.
     *
     * @return String message.
     */
    @Override
    public String toString() {
        return super.toString() + "You are missing the keyword";
    }
}
