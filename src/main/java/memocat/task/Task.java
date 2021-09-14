package memocat.task;

/**
 * A task representation for memocat.
 */
public abstract class Task implements Comparable<Task> {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets status icon of this task.
     *
     * @return "X" if this task is done, otherwise " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }

    /**
     * Gets the done status of this task.
     *
     * @return True if this task is done, false otherwise.
     */
    public boolean getDoneStatus() {
        return this.isDone;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Task) {
            Task otherTask = (Task) other;
            return this.description.equals(otherTask.description) && (this.isDone == otherTask.isDone);
        }
        return false;
    }

    @Override
    public abstract int compareTo(Task task);
}
