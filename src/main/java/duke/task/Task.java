package duke.task;

/**
 * Represents a Task keyed in by the user.
 */
public class Task {
    private String taskName;
    private boolean isDone;
    private boolean isEmpty = true;

    public Task() {}

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        this.isEmpty = false;
    }

    /**
     * Marks a Task object as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public boolean isEmpty() { return isEmpty; }

    /**
     * Returns a checkbox in a string, representing the status of the task.
     *
     * @return string representing status of a Task
     */
    public String getCheckBox() {
        if (isDone) {
            return "[X]";
        }
        return "[ ]";
    }

    /**
     * Returns a string representation of a Task object.
     *
     * @return string representation of a Task object
     */
    @Override
    public String toString() {
        String result = this.getCheckBox() + " " + this.taskName;
        return result;
    }
}
