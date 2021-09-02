package duke.commands;

/**
 * Encapsulates a Task that has a description and a completion state isDone.
 * To be implemented by Deadline, Event and Todo.
 *
 * @author Owen Tan
 * @version duke.Duke Level-9
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description Description to be stored in a Task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Overloaded Constructor for Task.
     *
     * @param description Description to be stored in a Task.
     * @param isDone Completion state.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }


    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Completes a Task.
     */
    public void completeTask() {
        isDone = true;
    }

    /**
     * Returns a string representation of Task.
     *
     * @return A string representation of Task.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    /**
     * Checks if the description contains a keyword.
     *
     * @param keyword Keyword to be searched in the description.
     * @return A boolean representing if the description .
     */
    public boolean containsKeyword(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Returns a string formatted for saving purposes.
     * @return A string representation of Task for saving.
     */
    public abstract String printInSaveFormat();
}
