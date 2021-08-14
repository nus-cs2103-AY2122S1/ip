/**
 * Represents a user task.
 *
 * @author Jay Aljelo Saez Ting
 */
public class Task {

    private String description;
    private boolean isDone;

    /**
     * Creates a task with the given task description.
     *
     * @param taskDescription The description of the task.
     */
    public Task(String taskDescription) {
        this.description = taskDescription;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void complete() {
        isDone = true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (isDone) {
            sb.append("[X]");
        } else {
            sb.append("[ ]");
        }
        sb.append(" ");
        sb.append(description);
        return sb.toString();
    }
}
