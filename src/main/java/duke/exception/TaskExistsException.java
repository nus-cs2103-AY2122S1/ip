package duke.exception;

import duke.task.Task;

/**
 * Throws exception when task of same name exists.
 */
public class TaskExistsException extends DukeException {

    public TaskExistsException(Task.TaskTypes taskTypes, String taskStr) {
        super("Meow? " + taskTypes + " " + taskStr + " already exists.");
    }

}
