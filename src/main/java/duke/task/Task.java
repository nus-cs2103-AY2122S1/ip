package duke.task;

/**
 * Class that encapsulates a task.
 */
public class Task {
    private String taskName;
    private boolean isDone;

    /**
     * Returns a new Task object.
     * @param taskName The user input.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Returns a new Task object.
     * @param taskName The user input.
     * @param isDone Whether the task is done.
     */
    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone() {
        this.isDone = true;
    }

    /**
     * Overrides the toString method in Object.
     * @return The String representation of the Task object.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.taskName;
        } else {
            return "[ ] " + this.taskName;
        }
    }
}
