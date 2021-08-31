package duke;

/**
 * Encapsulates a task that only contains a description, classified as a todo.
 */
public class Todo extends Task {

    public Todo(String description, boolean completionStatus) {
        super(description, completionStatus);
    }

    @Override
    public String typeIcon() {
        return "[T]";
    }
}
