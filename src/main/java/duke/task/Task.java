package duke.task;

/**
 * Encapsulates a duke.task.Task with a String description.
 */
public class Task {
    /** The description of the duke.task */
    private String description;

    /** The state of whether the duke.task has been done */
    private boolean isDone;

    /**
     * Constructor of a duke.task.Task object.
     *
     * @param description Description of the duke.task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the checkmark for whether the duke.task is done.
     *
     * @return The whether the duke.task is done.
     */
    private String status() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Marks the duke.task as done.
     */
    public void done() {
        this.isDone = true;
    }


    public String saveData() {
        String doneState = this.isDone ? "1" : "0";

        return doneState + " " + this.description;
    }

    /**
     * Returns the string form of the duke.task object.
     *
     * @return The string form of the duke.task.
     */
    @Override
    public String toString() {
        return String.format("[%s] ", this.status()) + description;
    }
}
