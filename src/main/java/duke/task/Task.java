package duke.task;

/**
 * An abstract class representing a task
 */
public abstract class Task {
    /** The task that needs to be completed */
    private final String action;
    /** Whether a task is completed */
    private boolean isCompleted;
    private String tag;

    /**
     * Constructor of the task. Can only be called by subclasses
     * as Task is an abstract class.
     *
     * @param action task that needs to be completed.
     */
    public Task(String action) {
        this.action = action;
        this.isCompleted = false;
    }

    /**
     * Returns the action
     *
     * @return action
     */
    public String getAction() {
        return this.action;
    }

    /**
     * Returns whether a task is completed.
     *
     * @return whether a task is completed
     */
    public boolean isComplete() {
        return this.isCompleted;
    }

    /**
     * Changes the state of the task to be completed.
     */
    public void complete() {
        this.isCompleted = true;
    }

    /**
     * Abstract method for the task to return its save format
     *
     * @return Save format of the task
     */
    public abstract String toSaveFormat();

    /**
     * Returns whether the task action contains the search terms.
     *
     * @param searchTerms Terms to search for
     * @return true if search terms are found
     *         false otherwise.
     */
    public boolean contains(String searchTerms) {
        return this.action.contains(searchTerms);
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * Returns a string representing the task
     *
     * @return String representation of the task
     */
    @Override
    public String toString() {
        return String.format("%s[%s] %s",
                this.tag == null ? "" : "(#" + this.tag + ")",
                isCompleted ? "X" : " ",
                this.action
        );
    }
}
