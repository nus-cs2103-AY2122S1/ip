package duke.task;

/** The class that encapsulates a Task that has to be done. */
public class Todo extends Task {

    /**
     * Initializes the description of the task.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
