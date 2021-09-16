package duke.task;

/**
 * Represents the Task to be done.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a Task object with given description.
     *
     * @param description Description of Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon of the Task.
     *
     * @return "X" if done, else " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the Task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Gets the status icon of the Task in a format suitable to save.
     *
     * @return "1" if done, else "0".
     */
    public String printStatusIcon() {
        return (isDone) ? "1" : "0";
    }

    /**
     * Converts Task object into String form to save.
     *
     * @return {StatusIcon}|{description}.
     */
    public String convertToString() {
        return printStatusIcon() + "|" + description;
    }

    /**
     * Checks if description of Task has given keyword.
     *
     * @param keyword Keyword to check if description of Task contains.
     * @return true if Task contains keyword, else false.
     */
    public boolean hasKeyword(String keyword) {
        String[] deconstructedDescription = description.split(" ");

        for (int i = 0; i < deconstructedDescription.length; i++) {
            if (deconstructedDescription[i].equals(keyword)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Converts Task object into its String representation.
     *
     * @return String representation of Task object.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
