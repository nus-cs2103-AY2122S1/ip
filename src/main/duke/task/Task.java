package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

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
     * @see duke.Parser#fileContentsToTask(String)
     * @return a string to store in local file that represents the task.
     */
    public String populateSaveData() {
        return "T | " + (isDone ? 1 : 0) + " | " + description;
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }
}