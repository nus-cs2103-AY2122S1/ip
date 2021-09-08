package retriever.task;

/**
 * This class keeps track of tasks with a deadline.
 */
public class Deadline extends Task {
    private TaskDateAndTime by;

    /**
     * Constructor, to initialize a deadline task.
     *
     * @param description Description of the task.
     * @param by Time to do the task by.
     */
    public Deadline(String description, TaskDateAndTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a boolean suggesting if the deadline task
     * is due on the same date, as the date entered.
     *
     * @param date The date entered by the user, to be checked against.
     * @return A boolean, true, if the task is due on the same date as the date entered.
     */
    public boolean isOnDate(TaskDateAndTime date) {
        return date.equals(by);
    }

    /**
     * Returns the deadline task description and its status in an
     * organised format.
     *
     * @return A String including the deadline task details.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
