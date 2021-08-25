package duke.task;

import duke.exception.DukeException;
import duke.exception.DukeIllegalFormatException;

/**
 * Task class encapsulates a task.
 */
public abstract class Task {
    /**
     * Description of the task.
     */
    protected String description;
    /**
     * True if the task is done.
     */
    protected Boolean isDone;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description Description of the task.
     * @throws DukeException If the description does not follow the format.
     */
    public Task(String description) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeIllegalFormatException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        this.description = description;
        this.isDone = false;
    }
    
    /**
     * Marks this task as done.
     * Note it does not update the db.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }


    /**
     * Returns the string representation of the task to be saved in the db.
     *
     * @return String representation of the task to be saved in the db.
     */
    public String toDataString() {
        return " | " + (this.getStatusIcon().equals("X") ? '1' : '0') + " | " + this.description;
    }

    private String getStatusIcon() {
        return isDone ? "X" : " ";
    }
}