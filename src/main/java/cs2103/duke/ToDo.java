package cs2103.duke;

/**
 * This class encapsulates a Todo Task object, which inherits from the Task class.
 */
public class ToDo extends Task {
    protected int index;
    protected String description;

    public ToDo(int index, String description) {
        super(index, description);
        this.index = index;
        this.description = description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}