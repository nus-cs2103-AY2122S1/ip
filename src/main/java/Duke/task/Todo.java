package Duke.task;

/**
 * Represents a todo task.
 */
public class Todo extends Task {

    protected String type;

    public Todo(String description) {
        super(description);
        this.type = "Todo";
    }

    /**
     * Returns the string representation of the todo task in the text file
     * @return string representation of the todo task in the text file
     */
    public String addToFile() {
        return "T | 0 | " + this.description;
    }

    /**
     * Returns the string representation of the task to be shown in the Duke app
     * @return the string representation of the task to be shown in the Duke app
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}