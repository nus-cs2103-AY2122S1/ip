public class Task {

    // The name of the task.
    private String taskName;

    /***
     * Constructor to create a task.
     * @param taskName The name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
    }

    /***
     * Returns the string representation of the task.
     * @return The name of the task.
     */
    @Override
    public String toString() {
        return taskName;
    }
}
