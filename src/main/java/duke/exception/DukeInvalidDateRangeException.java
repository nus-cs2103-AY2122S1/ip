package duke.exception;

public class DukeInvalidDateRangeException extends DukeException {
    /**
     * Obtain the string representation of the exception.
     *
     * @return string representation of the exception
     */
    @Override
    public String toString() {
        return String.format("%s End time must be after the start time!", super.toString());
    }
}
