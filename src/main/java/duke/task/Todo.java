package duke.task;

public class Todo extends Task {

    /**
     * Constructor for a todo object.
     *
     * @param description the description of the todo object.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Updates the description of the Todo.
     *
     * @param description the description of the todo task.
     * @return new Todo with updated description and due date.
     */
    @Override
    public Todo update(String description) {
        return new Todo(description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
