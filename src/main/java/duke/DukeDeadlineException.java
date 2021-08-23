package duke;

public class DukeDeadlineException extends DukeException {
    @Override
    public String getMessage() {
        return "OOPS!!! The description of a deadline cannot be empty.";
    }
}
