public class Deadline extends Task{

    private final String BY;

    /**
     * A constructor for this deadline Task.
     *
     * @param description the description of what the task is.
     * @param by the specific date/time that this task has to be done by.
     */
    public Deadline(String description, String by) {
        super(description);
        this.BY = by;
    }

    /**
     * Returns the string representation of this Task, which follows the following format:
     * [D][Task status] Task Description (by: Task deadline)
     *
     * @return string representation of this Task, which is the type of task (Deadline),
     *         its status, its description, and its deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + BY + ")";
    }

    public String getBy() {
        return this.BY;
    }

}
