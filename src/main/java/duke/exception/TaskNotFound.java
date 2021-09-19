package duke.exception;

/**
 * Throws exception when Task cannot be found in task list.
 */
public class TaskNotFound extends DukeException {

    public TaskNotFound(String taskStr) {
        super("Meow? I can't find " + taskStr);
    }

}
