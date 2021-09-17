package duke.tasks;

/**
 * The ToDo class encapsulates a todo.
 */
public class ToDo extends Task {
    /**
     * Creates a todo task with a task name.
     *
     * @param taskName Task name for the todo.
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return "ToDo: " + super.toString();
    }
}
