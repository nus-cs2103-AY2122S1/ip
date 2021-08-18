public class Deadline extends Task{

    private String by;

    /**
     * A constructor for this deadline Task.
     *
     * @param description The description of what the task is.
     * @param by The specific date/time that this task has to be done by.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of this Task, which follows the following format:
     * [D][Task status] Task Description (by: Task deadline)
     *
     * @return A string representation of this Task, which is the type of task (Deadline),
     *         its status, its description, and its deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

}
