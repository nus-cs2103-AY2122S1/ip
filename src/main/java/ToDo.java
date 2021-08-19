/**
 * Todo is a task without any date/time attached to it.
 */
public class ToDo extends Task {
    /**
     * Creates a ToDo with the provided description.
     * @param description The description of the ToDo.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Converts the ToDo into a String that represents the ToDo.
     * @return The String representation of a ToDo.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
