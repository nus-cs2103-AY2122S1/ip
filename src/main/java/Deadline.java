import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A type of Task. Inherits from Task, takes in a deadline that
 * specifies when task has to be completed by.
 */
public class Deadline extends Task{
    private LocalDate deadline;

    /**
     * Constructor for Deadline. Takes in a description and a deadline.
     *
     * @param description
     * @param deadline
     */
    public Deadline(String description, LocalDate deadline) {
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
        return "[D]" + super.toString() + " (by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
