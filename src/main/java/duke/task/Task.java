package duke.task;

public abstract class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructor of the duke.task.Task class
     *
     * @param description description of this task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks this task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string representation of this task
     *
     * @return string representation of this task
     */
    @Override
    public String toString() {
        String taskType = "[" + this.getTaskType() + "]";
        String isDone = "[" + (this.isDone ? "X" : " ") + "]";
        return taskType + " " + isDone + " " + this.description;
    }

    public abstract String toSavableFormat();

    protected abstract String getTaskType();
}
