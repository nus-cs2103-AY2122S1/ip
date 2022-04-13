package duke;

/**
 * Class that extends task.
 * Allow users to add description.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description, "Todo");
    }

    @Override
    public String toString() {
        final String prefix = "[T]";
        return prefix + super.toString();
    }
}
