package duke.task;

/**
 * A class representing a Task.
 */
public abstract class Task {
    /** Boolean representing if the Task is done */
    private boolean isDone;
    /** The details of the task */
    private String taskDetails;

    /**
     * Constructs a Task object that is not done and has empty task details.
     */
    public Task() {
        this.isDone = false;
        this.taskDetails = "";
    }

    /**
     * Constructs a Task object that is not done with the given task details.
     */
    public Task(String taskDetails) {
        this.isDone = false;
        this.taskDetails = taskDetails;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        isDone = true;
    }

    public String getTaskDescription() {
        return taskDetails;
    }

    public void setTaskDetails(String details) {
        this.taskDetails = details;
    }

    /**
     * Returns an integer depending on if the task is done.
     *
     * @return integer 0 if the task is done and integer 1 if the task is not done.
     */
    public int isDoneToInt() {
        return isDone ? 0 : 1;
    }

    /**
     * Returns a letter as a String that represents the task type.
     *
     * @return A letter as a String that represents the task type.
     */
    public abstract String getTaskType();

    /**
     * Sets the date and time of the event
     *
     * @param date
     * @param time
     */
    public abstract void setDateAndTime(String date, String time);

    /**
     * Returns the String representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + taskDetails;
        } else {
            return "[ ] " + taskDetails;
        }
    }
}
