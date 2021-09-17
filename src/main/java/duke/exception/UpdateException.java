package duke.exception;

/**
 * The exception thrown when the user attempts to invoke the Update Command with an invalid input.
 */
public class UpdateException extends DukeException {
    @Override
    public String getMessage() {
        return "Oh no! I don't understand what that means :(\n"
                + "To update a task, input in the following format:\n"
                + "update <index of task> <command to add updated task>";
    }
}
