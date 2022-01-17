package calico.command;

// import duke packages
import calico.task.Task;

/**
 * Represents a task that is pending completion.
 * A <code>Todo</code> consists of a description.
 */
public class Todo extends Task {
    /**
     * Creates a Todo task.
     *
     * @param description Description about the task.
     */
    public Todo(String description) {
        super(description, 'T');
        this.name = "todo";
    }

    /**
     * Converts Todo task to string format.
     *
     * @return Todo as a string.
     */
    @Override
    public String toString() {
        return "[" + this.getCat() + "]" + super.toString();
    }
}
