package duke.task;

/**
 * An abstract Task class.
 */
public abstract class Task {
    private String taskDetails;
    private boolean isDone = false;


    /**
     * Class constructor for the Task class.
     *
     * @param taskDetails The main input details for the task.
     */
    public Task(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    /**
     * Method to set a task to be done.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Getter method to obtain the details of the task.
     *
     * @return The details of the task.
     */
    public String getTaskName() {
        return taskDetails;
    }

    /**
     * Getter method to obtain if the task is labled as done.
     *
     * @return A boolean value stating if the task is done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Getter method to obtain the tag/type of the task.
     *
     * @return The tag of the task in String form.
     */
    public abstract String getTag();

    /**
     * Getter method to obtain additional information of tasks such as at: and by:
     *
     * @return The additional information in String format.
     */
    public abstract String getAdditionalInfo();

    /**
     * To String method for tasks.
     *
     * @return The task in String format.
     */
    @Override
    public String toString() {
        String completedBox;
        if (isDone) {
            completedBox = "[X]";
        } else {
            completedBox = "[ ]";
        }
        return completedBox + " " + taskDetails;
    }
}
