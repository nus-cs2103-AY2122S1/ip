package duke.exception;

/**
 * Thrown to indicate that the task index is missing when the user enters
 * a command that requires an index to access the task list.
 */
public class MissingTaskIndexException extends DukeException {

    /**
     * Constructs a MissingTaskIndexException with a detail message.
     */
    public MissingTaskIndexException() {
        super("Task index is not specified!\n" + "Please follow this format: [done/delete] [index]");
    }
}
