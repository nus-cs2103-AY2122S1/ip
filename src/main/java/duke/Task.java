package duke;

/**
 * A representation of task with description and status
 *
 * @author Chen Yanyu
 */

abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Public Constructor for Task.
     *
     * @param description the description for the task.
     */
    public Task(String description) throws EmptyDescriptionException {
        if (description.isBlank()) {
            throw new EmptyDescriptionException();
        } else {
            this.description = description;
            this.isDone = false;
        }
    }

    /**
     * return the string representation of the task status.
     *
     * @return X if the task is done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * return the description.
     *
     * @return the description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * return the status of the task.
     *
     * @return the status of the task
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Mark the task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() +
                "] " + this.description;
    }

    /**
     * return the save string arrays for file writing
     *
     * @return the array of Strings representing the task
     */
    abstract public String[] saveStrings();
}
