package duke.commands;

/**
 * Encapsulates a Todo object that implements a Task.
 *
 * @author Owen Tan
 * @version Duke Level-9
 */
public class Todo extends Task {
    /**
     * Public constructor for Todo.
     *
     * @param description Description to be stored.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Overloaded public constructor for Todo.
     *
     * @param description Description to be stored.
     * @param isDone Boolean that represents Todo completion status.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of Todo.
     *
     * @return A string representation of Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string formatted for saving purposes.
     *
     * @return A string representation of Todo for saving.
     */
    @Override
    public String printInSaveFormat() {
        String[] info = {"T", isDone ? "1" : "0", description};
        return String.join(" | ", info);
    }
}
