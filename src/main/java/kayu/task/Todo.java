package kayu.task;

/**
 * Represents the simplest {@link kayu.task.Task} which only has a description.
 */
public class Todo extends Task {

    /** Keyword for Todo {@link kayu.task.Task} class. */
    public static final String KEYWORD = "T";

    /**
     * Initializes the Todo {@link kayu.task.Task}.
     *
     * @param description String description of Task.
     * @param isDone Boolean true if complete, else false.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Initializes the Todo {@link kayu.task.Task}. Overloaded constructor that sets
     * <code>isDone</code> field to false.
     *
     * @param description String description of Task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toEncodedString() {
        return KEYWORD + SPLIT_TEMPLATE + super.toEncodedString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return '[' + KEYWORD + ']' + super.toString();
    }
}
