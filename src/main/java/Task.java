public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * A method that returns the status icon of the task.
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
        Printer.prettyPrint("Nice! I've marked this task as done:\n\t   " +
                this.getStatusIcon() +
                " " +
                this.description);
    }

    public String toString() {
        return String.format("%s %s", this.getStatusIcon(), this.description);
    }
}