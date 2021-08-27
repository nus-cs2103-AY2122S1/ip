package duke.task;

public class Task {
    private String taskName;
    private boolean status;

    /**
     * Constructor for Task
     *
     * @param taskName name of the input task
     * @param status current status of task
     */
    Task(String taskName, boolean status) {
        this.taskName = taskName;
        this.status = status;
    }

    /**
     * Gets the current status of task.
     *
     * @return the current status of task
     */
    public String getStatus() {
        return status ? "1" : "0";
    }

    /**
     * Displays the Task
     *
     * @return String which includes task name and status
     */
    public String displayTask() {
        String display;
        if (status) {
            display = "|1|" + taskName;
        } else {
            display = "|0|" + taskName;
        }
        return display;
    }

    /**
     * Marks the task as completed
     */
    public void checkOffTask() {
        status = true;
    }
}
