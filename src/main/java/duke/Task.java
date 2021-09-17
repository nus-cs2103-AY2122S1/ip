package duke;

/**
 * A  generic task.
 */
public abstract class Task {
    protected static final String COMPLETED = "[X]";
    protected static final String NOT_COMPLETED = "[ ]";

    protected final String description;
    protected boolean isDone;

    /**
     * Creates a Task object.
     *
     * @param str description of a task.
     */
    public Task(String str) {
        this.description = str;
        this.isDone = false;
    }

    /**
     * Marks this task as done.
     */
    protected void markDone() {
        isDone = true;
    }

    /**
     * Retrieves the type of the task as a string.
     *
     * @return String that represents the task.
     */
    protected abstract String getTaskType();

    /**
     * Returns the date associated with the Task object.
     *
     * @throws DukeNoDateException Task has no date associated to it
     */
    protected String getDate() throws DukeNoDateException {
        throw new DukeNoDateException();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            // Casting is valid as the instance of the object is explicitly checked
            return description.equals(((Task) obj).description);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return (isDone ? COMPLETED : NOT_COMPLETED) + " " + description;
    }
}
