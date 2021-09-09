package duke.exception;

public class UpdateException extends DukeException {
    @Override
    public String getMessage() {
        return "Oh no! I don't understand what that means :(\n"
                + "To update a task, input in the following format:\n"
                + "update <index of task> <command to add updated task>";
    }
}
