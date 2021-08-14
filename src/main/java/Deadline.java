/**
 * This class encapsulates a Deadline.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class Deadline extends Task {
    private final String deadline;

    /**
     * Instantiates a new Deadline task.
     *
     * @param description the description of the deadline task.
     * @param deadline the deadline
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * String representation of a Deadline.
     *
     * @return String representation of a Deadline.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline);
    }
}
