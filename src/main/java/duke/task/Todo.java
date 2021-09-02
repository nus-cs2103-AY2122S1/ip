package duke.task;

/**
 * The type Todo that only has a task description.
 */
public class Todo extends Task {

    /** Identifying tag 'T' for Event task */
    private final String identifier = "T";

    /**
     * Instantiates a new Todo.
     *
     * @param description the task description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Prints out Todo task with an identifier and a done marker.
     *
     * @return String.
     */
    @Override
    public String toString() {
        String result = "[" + identifier + "]";
        result += super.toString();
        return result;
    }

    @Override
    public String toDatabaseString() {
        String result = identifier + "|";
        result += getStatus() ? "1|" : "0|";
        result += getDescription();
        return result;
    }
}
