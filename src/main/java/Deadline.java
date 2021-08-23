import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructor of the Deadline class
     *
     * @param description description of this deadline
     * @param by the due date of the deadline
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of this deadline
     *
     * @return string representation of this deadline
     */
    @Override
    public String toString() {
        String formattedDate = this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D] " + super.toString() + " (by: " + formattedDate + ")";
    }
}