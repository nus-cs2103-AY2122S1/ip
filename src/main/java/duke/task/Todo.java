package duke.task;

/**
 * Represents a Todo object.
 *
 * @author: James Kua
 * @version: Duke-Level-8
 */
public class Todo extends Task {

    /**
     * Constructor for creating a Todo task.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Formats Todo based on the user's input for saving.
     *
     * @return The formatted String for Todo.
     */
    public String formatSave() {
        return "T |" + ((super.isDone) ? " 1 | " : " 0 | ") + super.getDescription();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
