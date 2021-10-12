package duke.task;

/**
 * A task that needs to be done.
 */
public class ToDo extends Task {

    /**
     * Public constructor to create a ToDo object.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the String representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the formatted String representation
     * of the task to write into the save file.
     *
     * @return Formatted String representation.
     */
    @Override
    public String formatSave() {
        return "T%," + isDone + "%," + description;
    }
}
