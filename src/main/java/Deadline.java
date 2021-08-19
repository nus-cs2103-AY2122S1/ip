/**
 * This class represents a deadline task.
 */

public class Deadline extends Task{
    /** Deadline time of the task. */
    private String deadlineTime;

    /**
     * Constructs a Deadline instance using the given description and deadline time.
     *
     * @param description the given description.
     * @param deadlineTime the given deadline time.
     */
    public Deadline(String description, String deadlineTime) {
        super(description);
        this.deadlineTime = deadlineTime;
    }

    /**
     * Returns a string representation of the deadline.
     *
     * @return a string representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadlineTime + ")";
    }
}
