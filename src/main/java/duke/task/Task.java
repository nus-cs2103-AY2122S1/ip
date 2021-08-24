package duke.task;

/** A task class which represents the user's tasks. */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * A constructor for class Task.
     *
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Return the status icon of the task.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Mark a task as done, then show a message telling the user that the task has been marked done successfully.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Return a string representation in the format to be written in tasks.txt file.
     *
     * @return The string representation in the format to be written in tasks.txt file.
     */
    public String toDataString() {
        return String.format("| %d | %s", this.isDone ? 1 : 0, this.description);
    }

    /**
     * Return a string representation of this task.
     *
     * @return The string representation of this task.
     */
    public String toString() {
        return String.format("%s %s", this.getStatusIcon(), this.description);
    }


}