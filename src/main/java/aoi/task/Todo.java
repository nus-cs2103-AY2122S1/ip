package aoi.task;

/**
 * Encapsulates a Todo object that implements a Task.
 *
 * @author Owen Tan
 * @version aoi.Aoi Level-9
 */
public class Todo extends Task {
    /**
     * Constructor for Todo.
     *
     * @param description Description to be stored.
     */
    public Todo(String description) {
        this(description, false, "");
    }

    /**
     * Constructor for Todo.
     *
     * @param description Description to be stored.
     * @param isDone Boolean that represents Todo completion status.
     */
    public Todo(String description, boolean isDone) {
        this(description, isDone, "");
    }

    /**
     * Constructor for Todo.
     *
     * @param description Description to be stored.
     * @param notes A string representation of notes.
     */
    public Todo(String description, String notes) {
        this(description, false, notes);
    }

    /**
     * Constructor for Todo.
     *
     * @param description Description to be stored.
     * @param isDone Boolean that represents Todo completion status.
     * @param notes A string representation of notes.
     */
    public Todo(String description, boolean isDone, String notes) {
        super(description, isDone, notes);
    }

    /**
     * Returns a string representation of Todo.
     *
     * @return A string representation of Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() + "\n  Notes: " + notes;
    }

    /**
     * Returns a string formatted for saving purposes.
     *
     * @return A string representation of Todo for saving.
     */
    @Override
    public String printInSaveFormat() {
        String[] info = {"T", isDone ? "1" : "0", description, notes};
        return String.join(" | ", info);
    }
}
