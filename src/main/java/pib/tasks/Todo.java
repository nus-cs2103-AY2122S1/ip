package pib.tasks;

import pib.Ui;
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
    private Todo(String description) {
        super(description);
    }

    private Todo(String description, int isDone) {
        super(description, isDone);
    }

    public static Todo createTodo(String details) throws PibException {
        if (details.trim().isBlank()) {
            Ui.printError("empty-task-description");
            throw new PibException("Task description can't be blank");
        } else {
            return new Todo(details.trim());
        }
    }

    public static Todo createTodo(String details, int isDone) {
        return new Todo(details.trim(), isDone);
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
