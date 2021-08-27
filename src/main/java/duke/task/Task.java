package duke.task;

import duke.DukeException;

public abstract class Task {

    /** Description of the task */
    private final String description;
    /** Status of the task */
    private boolean isDone;

    /**
     * Returns a new task.
     *
     * @param description Description of the task.
     * @throws DukeException If there is missing information or the declaration of the task is of the wrong format.
     */
    public Task(String description) throws DukeException {
        if (description.equals("")) {
            throw new DukeException(DukeException.Type.DESCRIPTION);
        }
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status of the task.
     *
     * @return "X" if the task is done, " " otherwise.
     */
    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns a formatted String representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatus() + "] " + this.description;
    }

    /**
     * Marks a task a completed.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the code of the task.
     *
     * @return The code representing a specified type of task.
     */
    public abstract String getCode();
}
