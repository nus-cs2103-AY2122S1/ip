package duke;

public class DukeTodoException extends DukeException {
    @Override
    public String getMessage() {
        return "OOPS!!! The description of a deadline cannot be empty.";
    }
}
