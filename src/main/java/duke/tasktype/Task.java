package duke.tasktype;

public interface Task {

    public void markComplete();

    /**
     * Method to return the corresponding status icon depending on
     * whether the task has been completed or not.
     * @return The String representation of the icon.
     */
    public String getStatusIcon();

    public String getDescription();

    /**
     * Method to return the corresponding type icon depending on
     * the type of the task.
     * @return
     */
    public String getTypeIcon();

    public String createData();
}
