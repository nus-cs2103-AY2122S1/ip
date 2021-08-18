public class Todo extends Task {
    /**
     * Constructor of a Todo task.
     *
     * @param title title of the Todo task
     */
    public Todo(String title) {
        super(title);
    }

    /**
     * Return a String representation of a Todo task.
     * Starts "[T]" to indicate that it is a Todo task.
     *
     * @return String representation of a Todo.
     */
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
