package duke.tasktypes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class.
 */
public class Deadline extends Task {

    private String by;
    private LocalDate date;

    /**
     * Constructor for Deadlines.
     * @param description
     * @param finishBy
     */
    public Deadline(String description, String finishBy) {
        super(description, "D");
        this.by = finishBy;
    }

    /**
     * Modifies inputted date.
     * @return Date in a different format.
     */
    public String understandDate() {
        date = LocalDate.parse(this.by);
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
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
