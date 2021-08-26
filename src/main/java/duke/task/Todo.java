package duke.task;

/**
 * Task to be completed
 */
public class Todo extends Task {

    /**
     * Class Constructor
     *
     * @param description description of task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Modifies the string representation of task
     *
     * @return string representation of task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
