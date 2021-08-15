/**
 * Task is an Object that consist of the status of completion and the task details.
 */
public class Task {
    /**
     * the status of the task
     */
    private boolean isComplete;

    /**
     * the details of the task
     */
    private final String taskDetails;

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
     * set isComplete as true
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
            return "[✓] " + taskDetails;
        } else {
            return "[ ] " + taskDetails;
        }
    }
}