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
     * String representation of the todo object
     * to be stored in the hard disk.
     *
     * @return A string representation of the todo object.
     */
    public String getStringFormat() {
        return "T|" + this.getStatusIcon() + "|" + this.description + "\n";
    }

    /**
     * String representation of the todo object to be
     * displayed to the user.
     *
     * @return Another string representation of the todo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
