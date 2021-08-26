package duke.task;

/**
 * This is a ToDo class that extends Task.
 */
public class ToDo extends Task {

    /**
     * This is a ToDo Constructor.
     *
     * @param description A String representing the details of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String fullCommand() {
        return "todo " + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
