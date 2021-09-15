package duke.task;

/**
 * Represents a todo with a description and done status.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo.
     * Creates a Todo with a description.
     *
     * @param description Todo description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns string identifier for a Todo.
     * String identifier for a Todo is "T".
     *
     * @return String identifier for Todo.
     */
    @Override
    public String getTaskType() {
        return "T";
    }

    /**
     * Returns a formatted string that begins with this Todo's string identifier, followed by its done status
     * and description.
     *
     * @return Formatted description of this Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Indicates whether another object is equals to this Todo.
     *
     * @param obj Other object to be compared to.
     * @return A boolean indicating whether the other object is equals to this Todo.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Todo) {
            Todo other = (Todo) obj;

            // Check is done status and description are the same.
            boolean isDoneStatusSame = this.isDone == other.isDone;
            boolean isDescriptionSame = this.description.equals(other.description);

            return (isDoneStatusSame && isDescriptionSame);
        }
        return false;
    }
}
