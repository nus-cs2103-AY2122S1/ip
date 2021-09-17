package duke.common.task;

import java.io.Serializable;

import duke.common.Duke;
import duke.common.enums.TaskField;

/**
 * Generic task that stores a description and whether the task is completed.
 */
public class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    /**
     * Default Task constructor
     *
     * @param description description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        assert this.description != "" : "Task description should not be empty";
    }

    protected String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Updates corresponding field of task to new user-provided value.
     *
     * @param taskField target field of task
     * @param newItem new user-provided value for target field
     * @return string representation of updated item
     * @throws Duke.DukeException indicates that a field that does not exist was requested to be updated.
     */
    public String update(TaskField taskField, String newItem) throws Duke.DukeException {
        if (taskField != TaskField.DESCRIPTION) {
            throw new Duke.DukeException("Sorry, there does not seem to be such a field in this task.");
        }
        this.description = newItem;
        return this.toString();
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Getter for task description.
     *
     * @return description of task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Changes task completion state to true.
     */
    public void complete() {
        this.isDone = true;
    }
}
