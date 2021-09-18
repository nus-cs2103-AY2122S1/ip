package duke.task;

import duke.error.DukeException;

/**
 * Represents a todo task.
 */
public class TodoTask extends Task {
    /**
     * Constructs a TodoTask object.
     *
     * @param description Description of the task.
     */
    public TodoTask(String description) {
        super(description);
        this.type = "T";
    }

    /**
     * Constructs a TodoTask object.
     *
     * @param description Description of the task.
     * @param isDone Status of the task.
     */
    public TodoTask(String description, boolean isDone) {
        super(description, isDone);
        this.type = "T";
    }
}
