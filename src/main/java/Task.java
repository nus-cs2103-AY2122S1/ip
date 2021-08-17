/**
 * This class encapsulates a task.
 */
public class Task {
    /**
     * The description of the task.
     */
    private String taskDescription;

    /**
     * Constructor for Task object, takes in taskDescription string.`
     * @param taskDescription Description of the task.
//     */
    private Task (String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Static method to create a new task.
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
}
