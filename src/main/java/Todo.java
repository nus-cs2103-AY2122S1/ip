public class Todo extends Task {
    /**
     * Constructor for Todo object.
     * @param description Description of Todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns string representation of Todo object.
     * @return String representation of Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
