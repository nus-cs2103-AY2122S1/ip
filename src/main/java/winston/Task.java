package winston;

/**
 * Represents the tasks that the user input
 */
public class Task {
    private String description;
    private boolean done;

    /**
     * Private constructor for Task
     *
     * @param description is the string of the description of the given task
     */
    public Task(String description, String type, boolean isCompleted) {
        this.description = description;
        this.done = isCompleted;
    }

    /**
     * Mark a task as complete
     */
    public void setComplete() {
        this.done = true;
    }

    /**
     * Check the completion of the task
     * @return "[X]" if task is completed and "[ ]" if the task isn't
     */
    public String taskCompletion() {
        if (done) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    /**
     * Method to convert information from object instance into a different format to be saved
     *
     * @return a string with completion status, task description
     */
    protected String saveFormat() {
        if (done) {
            return 1 + "," + this.description;
        } else {
            return 0 + "," + this.description;
        }
    }
    
    public boolean isSubString(String str) {
        return this.description.toLowerCase().indexOf(str.toLowerCase()) >= 0;
    }
    

    /**
     *  Method to convert information from object instance into a more readable format
     *
     * @return a string with the task description
     */
    @Override
    public String toString() {
        return this.description;
    }
}

