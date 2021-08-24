package catobot.item;

import catobot.exception.EmptyCommandException;

/**
 * Represents a type of task which has description only.
 */
public class Todo extends Task {
    /**
     * Constructor for Todo.
     *
     * @param description The details of Todo.
     * @throws EmptyCommandException If the description is empty.
     */
    private Todo(String description) throws EmptyCommandException {
        super(description);
    }

    /**
     * Creates a Todo.
     *
     * @param description The description of the Todo.
     * @return The created Todo catobot.item.
     * @throws EmptyCommandException if the description is empty.
     */
    public static Todo of(String description) throws EmptyCommandException {
        if (description.isEmpty()) {
            throw new EmptyCommandException("todo");
        } else {
            return new Todo(description);
        }
    }

    /**
     * Returns the string representation of Todo.
     *
     * @return The string representation of Todo, including status and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() ;
    }

    /**
     * Represents the format of todo in storage.
     *
     * @return The string representation of todo in storage.
     */
    @Override
    public String toStringInDoc() {
        String s = super.toStringInDoc();
        String s1 = String.format("T | %s", s);
        return s1;
    }
}
