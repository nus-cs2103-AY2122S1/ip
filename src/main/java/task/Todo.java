package task;

public class Todo extends Task {

    /**
     * Constructor for Todo.
     * @param description Text description of Todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Override toString method to show text representation of Todo.
     * @return String representing the Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
