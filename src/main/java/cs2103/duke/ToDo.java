package cs2103.duke;

/**
 * This class encapsulates a Todo Task object, which inherits from the Task class.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}