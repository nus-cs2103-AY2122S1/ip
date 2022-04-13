package shybot.task;

import shybot.exception.ShyBotException;

/**
 * Todo class encapsulates a todo task.
 */
public class Todo extends Task {
    private static final String LABEL = "T";

    /**
     * Constructs a Todo with the specified description.
     *
     * @param description Description of the todo.
     * @throws ShyBotException If the description does not follow the format.
     */
    public Todo(String description, String[] tags) throws ShyBotException {
        super(description, tags);
    }

    @Override
    public String toString() {
        return "[" + LABEL + "]" + super.toString() + " (tags: " + String.join(", ", this.tags)
            + ")";
    }

    @Override
    public String toDataString() {
        return LABEL + super.toDataString();
    }
}




