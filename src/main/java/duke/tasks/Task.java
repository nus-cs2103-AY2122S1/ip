package duke.tasks;

/**
 * Represents a Task in the task list.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task
     *
     * @param description description of the task
     * @param isDone whether this task is to be marked as done or not
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks this task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a string representation of this task to be written into the
     * text file
     *
     * @return string representation of this task to be written into the text file
     */
    public abstract String persistedDataStringFormat();

    /**
     * Returns a string of this task by prefixing "[X] {description}". It's subclasses will further
     * add in more characters to indicate more information.
     *
     * @return a string representation of this task
     */
    @Override
    public String toString() {
        String checkbox = this.isDone ? "[X] " : "[ ] ";
        return checkbox + this.description;
    }
}
