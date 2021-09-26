package duke.tasktypes;

/**
 * Task class, consisting of Deadlines, Events, ToDos.
 */
public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructor for Task class.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Changes status to input.
     *
     * @param result Changed Boolean.
     */
    public void changeStatus(boolean result) {
        this.isDone = result;
    }

    /**
     * Retrieves current status.
     *
     * @return Current status.
     */
    public boolean getBooleanStatus() {
        return this.isDone;
    }

    /**
     * Retrieves task's description.
     *
     * @return Task's description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Retrieves task's status in X or O.
     *
     * @return X or O.
     */
    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns if description contains user's input.
     *
     * @param input User's input.
     * @return Boolean.
     */
    public boolean containsMatch(String input) {
        return this.description.contains(input);
    }


    /**
     * Returns in a format to save to file.
     */
    public String saveToHardDisk() {
        return "";
    }

    /**
     * Returns string format.
     */
    @Override
    public String toString() {
        return ("[" + getStatus() + "] " + this.description);
    }
}
