package duke.task;

/**
 * This is a duke.task.ToDo class that extends duke.task.Task.
 */
public class ToDo extends Task {

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
