package duke.data.task;

/**
 * This class abstracts a ToDo task.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo task with the given description.
     *
     * @param description The String description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the data representation of a ToDo object.
     *
     * @return The formatted String.
     */
    @Override
    public String toData() {
        return "T|" + super.toData();
    }

    /**
     * Returns the String representation of a ToDo object.
     *
     * @return THe String representation of a ToDo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
