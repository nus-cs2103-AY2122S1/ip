package duke.exceptions;

import duke.tasks.Task;

public class DuplicateTaskException extends DukeException {

    /**
     * DuplicateTaskException constructor.
     *
     * @param task Task that is duplicated.
     */
    public DuplicateTaskException(Task task) {
        super(String.format("This task is already in the taskList!\n    %s", task.toString()));
    }
}
