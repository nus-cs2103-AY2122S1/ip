package task;

/**
 * Todo represents a Todo task.
 *
 * @author Ho Wen Zhong
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the String representation of the Todo.
     *
     * @return String representation of Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
