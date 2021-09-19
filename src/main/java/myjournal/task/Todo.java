package myjournal.task;

/**
 * Creates task with the type of todo.
 *
 * @author Felissa Faustine.
 */
public class Todo extends Task {
    /**
     * Constructs the Todo object.
     *
     * @param taskName The name of the task.
     */
    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * Gets the symbol of the todo task.
     *
     * @return The string representation of the symbol of the todo task.
     */
    public String getSymbol() {
        return "T";
    }

    /**
     * Returns the time of the todo task.
     *
     * @return The time of the todo task.
     */
    public String getTime() {
        return "";
    }

    /**
     * Sets the time of the task.
     *
     * @param time The time of the task.
     */
    public void setTime(String time) {
    }

    /**
     * Gets the string representation of the todo task.
     *
     * @return The string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[" + getSymbol() + "]" + super.toString();
    }
}
