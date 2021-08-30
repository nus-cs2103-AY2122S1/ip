package brobot.task;

/**
 * Represents a Todo Task.
 */
public class Todo extends Task {

    /**
     * Constructor.
     *
     * @param content Main content of the Todo task.
     */
    public Todo(String content) {
        super(content);
    }

    /**
     * String representation of a todo task.
     *
     * @return Todo task in string.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * String representation of a Todo for storage.
     *
     * @return Todo task in String(Storage format).
     */
    public String toStorageString() {
        String s1 = super.toStorageString();
        String s2 = String.format("T %s", s1);
        return s2;
    }
}
