package duke;

/**
 * A Task of type "Todo"
 */
public class Todo extends Task {

    /**
     * Constructor of the class
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of a Todo task
     * @return the string representation of a Todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
