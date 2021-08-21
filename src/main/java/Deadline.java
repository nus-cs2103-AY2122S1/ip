public class Deadline extends Task {

    // Time of deadline
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return string representation of deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns the string representation which is use to store the tasks.
     *
     * @return string representation used for storing task.
     */
    @Override
    public String toDataFormat() {
        return String.format("E | %s | %s | %s", isDone ? "1" : "0", description, by);
    }
}
