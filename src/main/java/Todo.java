/**
 * A task with no time constraint associated with.
 */
public class Todo extends Task{
    /**
     * Constructor for the Task class.
     * @param description Description of the task at hand.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Provides a String representation of the Todo.
     * @return A String representation of the Todo.
     */
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + this.description;
    }
}
