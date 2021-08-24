package lania.task;

/**
 * Represents the task class as a todo.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo which consists of description.
     *
     * @param description The name of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts the todo object to a string representation.
     *
     * @return The string representation of the todo object.
     */
    public String getStringFormat() {
        return "T|" + this.getStatusIcon() + "|" + this.description + "\n";
    }

    /**
     * Another string representation of the todo object.
     *
     * @return A string representation of the todo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
