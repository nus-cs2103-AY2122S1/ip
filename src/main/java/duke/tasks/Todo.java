package duke.tasks;

/**
 * A todo task.
 */
public class Todo extends Task {

    /**
     * Constructor of Todo.
     *
     * @param description description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Parses the Todo to a format appropriate for saving.
     *
     * @return a string containing only important information of the Todo to be saved.
     */
    @Override
    public String parseToSave() {
        return "T|" + super.parseToSave();
    }

    /**
     * toString method of Todo.
     *
     * @return toString description of Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
