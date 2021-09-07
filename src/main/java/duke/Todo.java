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
     * Constructs a Todo object.
     * @param description The description of the Todo object.
     * @param tag The tag associated to the Todo object.
     */
    public Todo(String description, String tag) {
        super(description, tag);
    }

    /**
     * Returns a string representation of the Todo object.
     * @return The string representation of the Todo object.
     */
    @Override
    public String toString() {
        return this.getTag().isEmpty()
                ? "[T][" + this.getStatusIcon() + "] " + this.getDescription()
                : "[T][" + this.getStatusIcon() + "] " + this.getDescription() + " " + this.getTag();
    }
}
