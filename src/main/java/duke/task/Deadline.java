package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline object.
 *
 * @author: James Kua
 * @version: Duke-Level-8
 */
public class Deadline extends Task {

    /** String that specifies the time of Deadline. */
    protected String by;
    /** String that specifies the date of the Deadline. */
    protected LocalDate date;
    /** String that specifies the timing of the Deadline. */
    protected LocalTime time;

    /**
     * Constructor for creating a deadline task.
     *
     * @param description The deadline's description.
     * @param by Time to complete the task by.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor for creating a deadline task.
     *
     * @param description The deadline' description.
     * @param date Date to complete by.
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }


    /**
     * Constructor for creating a deadline task.
     *
     * @param description The deadline' description.
     * @param date The deadline's description.
     * @param time Time to complete by.
     */
    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    /**
     * Returns time to be completed by.
     *
     * @return String representing the time.
     */
    public String getTime() {
        return by;
    }

    /**
     * Formats the deadline based on the user's input for saving.
     *
     * @return The formatted String.
     */
    public String formatSave() {
        if (time != null) {
            return "D | " + ((super.isDone) ? "1 | " : "0 | ") + super.getDescription() +  " | "
                    + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ", "
                    + time.format(DateTimeFormatter.ofPattern("h:mma"));
        } else if (date != null) {
            return "D | " + ((super.isDone) ? "1 | " : "0 | ") + super.getDescription() + " | "
                    + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } else {
            return "D | " + ((super.isDone) ? "1 | " : "0 | ") + super.getDescription() + " | " + getTime();
        }
    }

    @Override
    public String toString() {
        if (time != null) {
            return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern(
                    "MMM dd yyyy")) + ", " + time.format(DateTimeFormatter.ofPattern("h:mma")) + ")";
        } else if (date != null) {
            return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern(
                    "MMM dd yyyy")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }
}