package duke.task;

/**
 * ToDo class that handles todo tasks.
 */
public class ToDo extends Task {

    /**
     * Constructs the ToDo object.
     *
     * @param description Description of todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileFormat() {
        return "T / " + super.toFileFormat();
    }
}
