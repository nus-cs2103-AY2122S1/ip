/**
 * Represents a todo item.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    /**
     * @return String representation of a ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
