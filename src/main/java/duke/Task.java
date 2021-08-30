package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Method that returns the status icon of the task based on whether it is completed.
     * or not
     *
     * @return String Either "X" or " " based on the status
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Method that marks a task as done.
     *
     * @return void
     */
    public void markAsDone() {
        this.isDone = true;
    }
}