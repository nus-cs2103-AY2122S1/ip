package winston;

/**
 * Represents the tasks that the user input.
 */
public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Private constructor for Task.
     *
     * @param description is the string of the description of the given task.
     * @param isCompleted whether the task is completed or not.
     */
    public Task(String description, boolean isCompleted) {
        this.description = description;
        this.isDone = isCompleted;
    }

    /**
     * Mark a task as complete.
     */
    public void setComplete() {
        this.isDone = true;
    }

    /**
     * Check the completion of the task.
     * @return "[X]" if task is completed and "[ ]" if the task isn't.
     */
    public String taskCompletionStatus() {
        if (isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    /**
     * Method to convert information from object instance into a different format to be saved.
     *
     * @return a string with completion status, task description.
     */
    protected String saveFormat() {
        if (isDone) {
            return 1 + "," + this.description;
        } else {
            return 0 + "," + this.description;
        }
    }

    /**
     * A method that determines if a string can be found in this task's description.
     * @param str given string.
     * @return a boolean on whether a given string is a substring of the description.
     */
    public boolean isSubString(String str) {
        return this.description.toLowerCase().contains(str.toLowerCase());
    }
    
    /**
     *  Method to convert information from object instance into a more readable format.
     *
     * @return a string with the task description.
     */
    @Override
    public String toString() {
        return this.description;
    }
}

