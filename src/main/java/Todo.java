/**
 * This class represents a todo task.
 */

public class Todo extends Task{
    /**
     * Constructs a todo using the given description.
     *
     * @param description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo.
     *
     * @return a string representation of the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
