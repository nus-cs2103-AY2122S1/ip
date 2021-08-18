public class Task {

    private boolean isDone;
    private String taskDescription;

    /**
     * Constructor for a task.
     * @param taskDescription The name of the task.
     */
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    /**
     * Updates the task status of completion as done.
     */
    public void completeTask() {
        this.isDone = true;
        System.out.println("Nice! I have marked this task as done: \n"
                + this.toString());
    }

    /**
     * Gets the task name.
     * @return Task name.
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * A string representation of the task,
     * the status of completion and task name are provided in here.
     *
     * @return The string representation for the task.
     */
    public String toString() {
        return isDone ? "[x] " + taskDescription : "[ ] " + taskDescription;
    }
}
