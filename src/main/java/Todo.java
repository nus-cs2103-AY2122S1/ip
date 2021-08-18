/**
 * Represents a task without any date or time attached to it.
 * E.g. walk the dog.
 */
public class Todo extends Task {

    /**
     * Class constructor.
     * @param description Description of the Todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the Todo.
     *
     * @return String representation of Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
