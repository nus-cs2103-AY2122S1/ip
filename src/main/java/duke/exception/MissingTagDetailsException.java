package duke.exception;

/**
 * Thrown to indicate that either the task index or tag name is missing when the user enters
 * a command to tag a task in the task list.
 */
public class MissingTagDetailsException extends DukeException {

    /**
     * Constructs a MissingTagDetailsException with a detail message.
     */
    public MissingTagDetailsException() {
        super("Task index/Tag name is not specified!\n" + "Please follow this format: tag [index] [tag name]");
    }
}
