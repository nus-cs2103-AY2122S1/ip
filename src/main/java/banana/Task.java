package banana;

/**
 * The Task class handles tasks.
 *
 * @author: Ravi Ananya
 */
class Task {

    private String description;
    private boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description user input.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Gets the string notation of done
     * or not done.
     *
     * @return x for done or empty for not done.
     */
    public String getIsDone() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Gets the user input.
     *
     * @return the user input.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the task as done.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return getIsDone() + " " + description;
    }

}
