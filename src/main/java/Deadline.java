/**
 * A type of Task. Inherits from Task, takes in a deadline that
 * specifies when task has to be completed by.
 */
public class Deadline extends Task{
    private String deadline;

    /**
     * Constructor for Deadline. Takes in a description and a deadline.
     *
     * @param description
     * @param deadline
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns the string representation of the Deadline class.
     *
     * @return String representation of Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
