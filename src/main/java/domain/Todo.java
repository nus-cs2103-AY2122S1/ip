package domain;

/**
 * Encapsulates a regular task.
 */
public class Todo extends Task {
    public Todo(String name) {
        super(name);
        typeString = "T";
    }
}
