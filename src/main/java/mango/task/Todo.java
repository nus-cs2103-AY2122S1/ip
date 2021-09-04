package mango.task;

/**
 * Represents a task that needs to be done. A <code>Todo</code> object corresponds to a
 * <code>Task</code> that has a description and a completion status.
 */
public class Todo extends Task {

    /**
     * Constructor for a Todo.
     *
     * @param description The description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor for a TaskList, initialised with a completion status.
     *
     * @param description The description of the todo.
     * @param status The completion status of the todo.
     */
    public Todo(String description, String status) {
        super(description, status.equals("true"));
    }

    /**
     * Returns the type of task in a string.
     *
     * @return The string that represents a Todo task.
     */
    @Override
    public String getType() {
        return "T";
    }

    /**
     * Returns the description of the todo.
     *
     * @return The description of the todo.
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the string representation of the todo.
     *
     * @return The string representation of the todo.
     */
    @Override
    public String getSaveFormatString() {
        return String.format("%s:%s:%s:\n", this.getType(), this.getStatus(), this.description);
    }
}
