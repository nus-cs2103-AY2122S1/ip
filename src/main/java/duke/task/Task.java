package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task.
     * @param description Description of a task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a task.
     * @param description Description of a task.
     * @param isDone Has the task been done?
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    /**
     * Convert the task into a string to store in local file.
     * The fields are divide by "|"; second parameter is 1 if the task is done, 0 otherwise.
     * @return a string to store in local file that represents the task.
     * @see duke.Parser#fileContentsToTask(String)
     */
    public String populateSaveData() {
        return "T | " + (isDone ? 1 : 0) + " | " + description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }
}
