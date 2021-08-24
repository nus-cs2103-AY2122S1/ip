package duke.task;

/**
 * This class represents a to-do - a task without any date/time attached.
 */
public class Todo extends Task {
    public static final String IDENTIFIER = "T";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getType() {
        return IDENTIFIER;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
