package duke;

public class DukeDoneException extends DukeException {
    @Override
    public String getMessage() {
        return "OOPS!!! Please specify which task.";
    }
}
