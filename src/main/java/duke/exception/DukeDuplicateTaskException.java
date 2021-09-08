package duke.exception;

public class DukeDuplicateTaskException extends DukeException {
    /**
     * Obtain the string representation of the exception.
     *
     * @return string representation of the exception
     */
    @Override
    public String toString() {
        return String.format("%s There is a duplicate task in your list!", super.toString());
    }
}
