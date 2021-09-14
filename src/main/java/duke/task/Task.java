package duke.task;

public class Task {
    private String taskName;
    private boolean isDone;

    /**
     * Constructor for Task
     *
     * @param taskName name of the input task
     * @param isDone current status of task
     */
    Task(String taskName, boolean isDone) {
        assert taskName != "" : "Task Name should not be empty";

        this.taskName = taskName;
        this.isDone = isDone;
    }

    /**
     * Gets the name of task
     *
     * @return the input name of the task
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Sets the name of teh task
     *
     * @param taskName the input name of the task
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Gets the current status of task.
     *
     * @return the current status of task
     */
    public String getStatus() {
        return isDone ? "1" : "0";
    }

    /**
     * Displays the Task
     *
     * @return String which includes task name and status
     */
    public String displayTask() {
        String display;
        if (isDone) {
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
        isDone = true;
    }

    /**
     * Marks the task as incomplete
     */
    public void uncheckTask() {
        isDone = false;
    }
}
