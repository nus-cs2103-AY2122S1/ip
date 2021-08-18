public class Task {

    private boolean isDone;
    private final String taskDescription;

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
                + this);
    }

    /**
     * The output message when the task is deleted.
     */
    public void deleteMessage() {
        System.out.println("Noted. I've removed this task: \n"
                + this);
    }

    /**
     * Gets the task name.
     * @return Task name.
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * @return The string representation for the task.
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "x" : " ") + "] " + taskDescription;
    }
}
