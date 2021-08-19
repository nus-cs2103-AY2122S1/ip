public class Todo extends Task {

    /**
     * Constructor to create a Todo
     * @param description Description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
