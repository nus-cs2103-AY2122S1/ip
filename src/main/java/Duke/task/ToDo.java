package duke.task;

import java.time.LocalDateTime;

/**
 * Represents a todo task. A todo task only has a task description.
 */
public class ToDo extends Task {

    public ToDo(String details) {
        super(details);
    }

    /**
     * Returns date of task.
     * ToDo task has no date.
     *
     * @return null.
     */
    @Override
    public LocalDateTime getDate() {
        return null;
    }

    /**
     * Returns a String representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
