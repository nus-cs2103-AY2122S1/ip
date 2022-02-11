package duke.tasks;

/**
 * Handles tasks that have no dates.
 */
public class TodoTask extends Task {

    public TodoTask(String title) {
        super(title, Type.TODO);
    }

    /**
     * Get a long number representing the urgency (date) of a task.
     *
     * @return numeric value to be used to compare tasks.
     */
    @Override
    protected long getUrgency() {
        return Long.MAX_VALUE;
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
