package duke.task;

/**
 * Represents a To-do type Task.
 */
public class Todo extends Task {

    /**
     * Constructs a To-do Task with the given description.
     *
     * @param description Description of the To-do.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of this To-do, which follows the following format:
     * [T][Task status] Task Description
     *
     * @return String representation of this To-do, which consists of the type of Task (To-do),
     *         its status, and its description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a To-do Task based on the given description.
     *
     * @param input String containing the To-do description.
     * @return To-do constructed from the given description.
     */
    public static Task setTodo(String input) {
        return new Todo(input);
    }
}
