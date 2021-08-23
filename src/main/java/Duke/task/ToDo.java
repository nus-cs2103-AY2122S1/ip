package duke.task;

import java.time.LocalDate;

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
    public LocalDate getDate() {
        return null;
    }

    /**
     * Returns time of task.
     * ToDO task has no time.
     *
     * @return -1.
     */
    @Override
    public int getTime() {
        return -1;
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
