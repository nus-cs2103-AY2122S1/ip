package duke.tasks;

/**
 * Represents a todos entry in the task list.
 */
public class ToDos extends duke.tasks.Task {

    /**
     * Constructor for the ToDos.
     *
     * @param description description of this ToDos.
     * @param isDone whether this task is to be marked as done or not.
     */
    public ToDos(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String persistedDataStringFormat() {
        char isDone01 = this.isDone ? '1' : '0';
        return String.format("T,%c,%s", isDone01, this.description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
