package duke.task;

/**
 * Representing user's task stored in Duke's taskList.
 */
public class Task {
    private String taskName;
    private boolean isDone;

    /**
     * Constructor of Task.
     *
     * @param taskName The name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Constructor of Task.
     *
     * @param taskName The name of the task.
     * @param isDone Whether the task had been done or not.
     */
    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    /**
     * Gets the name of task.
     *
     * @return The name of the task.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Gets the information whether the task is done or not.
     *
     * @return Whether the task is done or not.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets the task as done.
     */
    public void setToDone() {
        this.isDone = true;
    }

    /**
     * Returns string representation of task.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        String finished = " ";
        if (this.isDone) {
            finished = "X";
        }
        return "[" + finished + "] " + this.taskName;
    }

    /**
     * Returns the representation of the task when it is stored.
     *
     * @return The representation of the task when it is stored.
     */
    public String toStoredString() {
        return taskName;
    }

    /**
     * Compares this object with a given object.
     *
     * @param comparedObject The object compared with this object.
     * @return Returns true if they are equal, false otherwise.
     */
    @Override
    public boolean equals(Object comparedObject) {
        if (!(comparedObject instanceof Task)) {
            return false;
        }
        Task comparedTask = (Task) comparedObject;
        return comparedTask.toString().equals(this.toString());
    }
}
