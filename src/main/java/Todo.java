/**
 * A type of task that does not have a specified date or time.
 */
public class Todo extends Task {

    private Todo(String taskName) {
        super(taskName, Type.TODO);
    }

    /**
     * Creates a new task that is to be done.
     * @param taskName The name of the task.
     * @return The todo task.
     */
    public static Todo newTodoTask(String taskName) {
        return new Todo(taskName.trim());
    }

    @Override
    public String taskDescription() {
        return this.getTaskName();
    }
}
