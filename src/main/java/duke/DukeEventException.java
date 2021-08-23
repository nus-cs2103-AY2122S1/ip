package duke;

public class DukeEventException extends DukeException {
    @Override
    public String getMessage() {
        return "OOPS!!! The description of an Event cannot be empty.";
    }
}
