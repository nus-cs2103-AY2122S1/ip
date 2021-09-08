package duke;

import java.util.ArrayList;

/**
 * Encapsulates a task that only contains a description, classified as a todo.
 */
public class Todo extends Task {

    public Todo(String description, boolean completionStatus, ArrayList<String> tags) {
        super(description, completionStatus, tags);
    }

    @Override
    public String typeIcon() {
        return "[T]";
    }

    @Override
    public String toString() {
        return super.toString() + super.getTags();
    }
}
