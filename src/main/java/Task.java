/**
 * Represents a user task.
 *
 * @author Jay Aljelo Saez Ting
 */
public class Task {
    private String description;

    /**
     * Creates a task with the given task description.
     *
     * @param taskDescription The description of the task.
     */
    public Task(String taskDescription) {
        this.description = taskDescription;
    }

    @Override
    public String toString() {
        return description;
    }
}
