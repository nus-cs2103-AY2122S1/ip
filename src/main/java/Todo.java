/**
 * A subclass of Task.
 */
public class Todo extends Task {
    /**
     * A constructor to instantiate a todo task.
     * @param description description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * A method that returns the string representation of this Todo Object.
     *
     * @return String representation of Deadline object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
