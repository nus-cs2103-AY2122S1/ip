public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the todo task.
     *
     * @return string representation of todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
