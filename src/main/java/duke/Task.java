package duke;

/**
 * Abstract class representing a task.
 */
public abstract class Task {
    private String description;
    private Boolean isCompleted;

    /**
     * Constructor.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    /**
     * Constructor.
     *
     * @param description Description of the task.
     * @param isCompleted If the task is completed.
     */
    public Task(String description, Boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public String getDescription() {
        return this.description;
    }

    public Boolean getStatus() {
        return this.isCompleted;
    }

    public void setStatus(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the task in the following format.
     * [[Task Status]] [Task Description].
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        String output = "";
        if (this.getStatus()) {
            output += "[x] ";
        } else {
            output += "[ ] ";
        }

        output += this.getDescription();
        return output;
    }

    /**
     * Returns the String representation of the file in save format.
     *
     * @return String representation of the file in save format.
     */
    public abstract String save();
}
