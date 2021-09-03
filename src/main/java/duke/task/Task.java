package duke.task;

/**
 * This is a Task Class that represent the Task user keys into Duke.
 */
public abstract class Task implements Comparable<Task> {

    /**
     * These are class field of a Task.
     */
    protected String description;
    protected boolean isDone;

    /**
     * This is a Task Constructor.
     *
     * @param description A String representing description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns "X" when a task is done and " " when it is not done.
     *
     * @return A String representing the completion status of a Task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a boolean indicating if the task is done.
     *
     * @return A boolean representing the completion status of the task
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the full command required to create this task.
     *
     * @return A String representing the command that created this task.
     */
    public abstract String fullCommand();

    /**
     * Returns a String representing the task description.
     *
     * @return A String representing the task description.
     */
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Task)) {
            return false;
        } else {
            Task other = (Task) o;
            return this.description.equals(other.description) && (isDone == other.isDone);
        }
    }
}

