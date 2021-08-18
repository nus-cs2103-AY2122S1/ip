package item;

import exception.BotException;
import exception.EmptyCommandException;

/**
 * Todo is a type of task which has description only.
 */
public class Todo extends Task {
    private Todo(String description) throws EmptyCommandException {
        super(description);
    }

    /**
     * Create a Todo
     * @param description The description of the Todo.
     * @return The created Todo item.
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
     * Return the string representation of Task.
     * @return The string representation of Task, including status and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() ;
    }
}
