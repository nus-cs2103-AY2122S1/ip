package blitz.tasks;

public class Task {

    private String description;
    private boolean isDone;

    /**
     * Creates task with given description.
     * @param description describes what the task is.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return X if task is done, and " " if task isn't done yet.
     */

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return description;
    }


    /**
     * Returns the string representation of the Task object.
     *
     * @return string representation of the Task object.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Task) {
            Task t = (Task) o;
            return t.getDescription().equals(description);
        }
        return false;
    }
}
