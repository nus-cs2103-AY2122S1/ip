package duke.task;

/**
 * Represents a Task that is to be done,
 * no date and time cutoff.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo Task.
     *
     * @param description String description of ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + " \n";
    }
}
