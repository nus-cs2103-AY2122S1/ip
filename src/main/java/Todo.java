/**
 * Represents a Todo object.
 */
public class Todo extends Task {
    /**
     * Todo constructor.
     *
     * @param description the description
     */
    private Todo(String description) {
        super(description);
    }

    /**
     * Factory Todo method.
     *
     * @param description the description
     * @return a new Todo object
     */
    public static Todo of(String description) {
        return new Todo(description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s %s", isDone ? "[X]" : "[ ]", description);
    }
}
