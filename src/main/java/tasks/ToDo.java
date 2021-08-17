package tasks;

/**
 * Represents a task without any date/time attached to it.
 */
public class ToDo extends Task{

    /**
     * Creates a ToDo with a specified description.
     * @param description The description of this ToDo.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns this task's status and description.
     * @return A string showing what the task is and its description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
