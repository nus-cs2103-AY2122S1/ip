package duke.task;

public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task object with the given description.
     *
     * @param description Description of the new Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the status of this Task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Return a boolean to convey the presence of the given content in the description of the current Task object.
     *
     * @param content Content whose presence is to be checked for.
     * @return Boolean to show presence.
     */
    public boolean hasContent(String content) {
        return description.contains(content);
    }

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }

    /**
     * String representation of the Task to be used for storage on the hard-disk purposes.
     *
     * @return String to be used for storage.
     */
    public String toStorage() {
        return (isDone + "%" + description + "\n");
    }
}
