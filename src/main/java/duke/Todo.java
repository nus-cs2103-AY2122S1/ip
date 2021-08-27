package duke;

/**
 * Implements a Todo object.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo object.
     * @param description The description of the Todo object.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo object.
     * @return The string representation of the Todo object.
     */
    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
