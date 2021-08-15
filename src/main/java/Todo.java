/**
 * A task that is to be done.
 */
public class Todo extends Task {
    /**
     * A constructor used to initialize the todo.
     *
     * @param description the description of the todo.
     */
    protected Todo(String description) {
        super(description);
    }

    /**
     * Return the string representation of todo.
     *
     * @return the string representation of todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
