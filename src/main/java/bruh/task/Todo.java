package bruh.task;

/**
 * Represents a to-do task item.
 */
public class Todo extends Task {
    private static final char SYMBOL = 'T';

    /**
     * Constructor for a to-do task item.
     *
     * @param description The description of the to-do.
     */
    public Todo(String description) {
        super(description, SYMBOL);
    }
}
