/**
 * This class encapsulates a task.
 */
public class Task {
    /**
     * The description of the task.
     */
    private String taskDescription;

    /**
     * Represents the completion status of a task.
     */
    private boolean isDone;

    /**
     * Constructor for Task object, takes in taskDescription string.`
     * @param taskDescription Description of the task.
//     */
    private Task (String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    /**
     * Factory method to create a new task.
     * @param taskDescription The description of the task.
     * @return A new task with the given task description
     */
    public static Task createTask(String taskDescription) {
        return new Task(taskDescription);
    }

    /**
     * Returns the description of the task.
     * @return Description of the task.
     */
    public String getTaskDescription() {
        return this.taskDescription;
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a string representing the completion status of the task.
     * @return A string representing task status.
     */
    public String getStatusIcon() {
        return this.isDone ? "[X]" : "[ ]";
    }
}
