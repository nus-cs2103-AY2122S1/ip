package captain.task;

import captain.DukeException.MissingDescriptionException;

/**
 * Represents a to-do task.
 *
 * @author Adam Ho
 */
public class Todo extends Task {
    /**
     * Creates a to-do task with the specified description.
     * @param description The task description.
     */
    public Todo(String description) throws MissingDescriptionException {
        super(description);
    }

    /**
     * Represents to-do task as a string.
     * @return A string representation of the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
