package duke.task;

/**
 * Represents a task to be done
 */
public class Todo extends Task {

    /**
     * Todo constructor.
     *
     * @param description Description of task to be done.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}