package duke.tasktypes;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class.
 */
public class Deadline extends Task {

    private final String by;

    /**
     * Constructor for Deadlines.
     *
     * @param description Description of deadline.
     * @param finishBy Deadline date.
     */
    public Deadline(String description, String finishBy) {
        super(description);
        this.by = finishBy;
    }

    /**
     * Modifies inputted date.
     *
     * @return Date in a different format.
     */
    public String understandDate() {
        try {
            LocalDate date = LocalDate.parse(this.by);
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeException exception) {
            System.out.println(exception.getMessage());
        }
        return "";
    }

    /**
     * Returns in a format to save to file.
     */
    @Override
    public String saveToHardDisk() {
        return "D/" + this.getBooleanStatus() + "/" + this.getDescription() + "/" + this.by;
    }

    /**
     * Returns string format.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + understandDate() + ")";
    }
}
