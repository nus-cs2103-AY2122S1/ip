package duke.task;

/**
 * Task is an Object that consist of the status of completion and the task details.
 */
public class Task {
    /**
     * The status of the task.
     */
    protected boolean isComplete;

    /**
     * The details of the task.
     */
    protected final String taskDetails;

    /**
     * Constructs a uncompleted task with the details as taskDetails
     *
     * @param taskDetails task name
     */
    public Task(String taskDetails) {
        this.taskDetails = taskDetails;
        this.isComplete = false;
    }

    /**
     * set isComplete as true.
     */
    public void completeTask() {
        this.isComplete = true;
    }

    /**
     * Returns the task Details with the prefix of the status of completion of the task
     *
     * @return A string representation of task details with the prefix of the status of completion of the task.
     */
    @Override
    public String toString() {
        if (isComplete) {
            return "[X] " + taskDetails;
        } else {
            return "[ ] " + taskDetails;
        }
    }

    /**
     * Returns the string representation for storing in text file.
     *
     * @return the string representation for storing in text file
     */
    public String toEncodedString() {
        int completeBinary = 0;
        if (this.isComplete) {
            completeBinary = 1;
        }
        return "G" + " | " + completeBinary + " | " + this.taskDetails + " | ";
    }
}
