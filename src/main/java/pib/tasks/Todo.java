package pib.tasks;

import pib.pibexception.PibException;

/**
 * Todo task which contains only the task description
 */
public class Todo extends Task {

    private Todo(String description, boolean printMessage) {
        super(description, printMessage);
    }

    private Todo(String description, int isDone, boolean printMessage) {
        super(description, isDone, printMessage);
    }

    /**
     * A public factory method to create a Todo task
     *
     * @param details description of the todo task
     * @param printMessage Boolean to indicate whether to print the success message after each Task is added
     * @return Todo object with description initialised and isDone set to 0
     * @throws PibException when user inputs blank task description
     */
    public static Todo createTodo(String details, boolean printMessage) throws PibException {
        if (details.trim().isBlank()) {
            throw new PibException("empty-task-description");
        } else {
            return new Todo(details.trim(), printMessage);
        }
    }

    /**
     * A public factory method to create a ToDo task with the isDone also initialised
     *
     * @param details description of the todo task
     * @param isDone  value 0 (false) or 1 (true)
     * @param printMessage Boolean to indicate whether to print the success message after each Task is added
     * @return Todo object with both description and isDone initialised
     */
    public static Todo createTodo(String details, int isDone, boolean printMessage) {
        return new Todo(details.trim(), isDone, printMessage);
    }

    /**
     * Public method to convert task to a string format used to save inside a .txt file
     *
     * @return string format of Todo task to be saved
     */
    public String toDataString() {
        return "T," + getIsDone() + "," + getDescription() + System.lineSeparator();
    }

    /**
     * A public toString method to add the task type [T] in front of the checkbox
     *
     * @return the string representation of a Todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
