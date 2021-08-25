package eightbit.task;

/**
 * Represents a todo item.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * @return String representation of a ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
