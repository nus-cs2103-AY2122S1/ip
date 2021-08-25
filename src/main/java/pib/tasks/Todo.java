package pib.tasks;

import pib.pibexception.PibException;

/**
 * Todo task which contains only the task description
 */
public class Todo extends Task {

    /**
     * A public constructor to create a ToDo task
     *
     * @param description description of the todo task
     */
    private Todo(String description, boolean printMessage) {
        super(description, printMessage);
    }

    private Todo(String description, int isDone, boolean printMessage) {
        super(description, isDone, printMessage);
    }

    public static Todo createTodo(String details, boolean printMessage) throws PibException {
        if (details.trim().isBlank()) {
            throw new PibException("empty-task-description");
        } else {
            return new Todo(details.trim(), printMessage);
        }
    }

    public static Todo createTodo(String details, int isDone, boolean printMessage) {
        return new Todo(details.trim(), isDone, printMessage);
    }

    public String toDataString() {
        return "T," + getIsDone() + "," + getDescription() + System.lineSeparator();
    }

    /**
     * A public toString method to add the task type [T] in front of the checkbox
     *
     * @return the string representation of a todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
