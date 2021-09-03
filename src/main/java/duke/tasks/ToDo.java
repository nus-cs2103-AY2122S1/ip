package duke.tasks;

/**
 * The ToDo class encapsulates a todo.
 */
public class ToDo extends Task {
    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return "ToDo: " + super.toString();
    }
}
