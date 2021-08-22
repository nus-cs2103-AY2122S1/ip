package duke.data.exception;

/**
 * Describes exception caused by empty time input
 */
public class EmptyTimeException extends DukeException {
    @Override
    public String toString() {
        return "OOPS!!! Please indicate a time for your Event or Deadline.";
    }
}
