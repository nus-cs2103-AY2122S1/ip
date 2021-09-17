package aoi.task;

import java.time.LocalDateTime;

import aoi.parser.Parser;

/**
 * Encapsulates a Deadline object that implements a Task and has a deadline.
 *
 * @author Owen Tan
 * @version aoi.Aoi Level-9
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructor for Deadline.
     *
     * @param description Description to be stored in Deadline.
     * @param by Deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        this(description, false, by, "");
    }

    /**
     * Constructor for Deadline.
     *
     * @param description Description to be stored in Deadline.
     * @param isDone Boolean that represents Deadline completion status.
     * @param by Deadline.
     */
    public Deadline(String description, boolean isDone, LocalDateTime by) {
        this(description, isDone, by, "");
    }

    /**
     * Constructor for Deadline.
     *
     * @param description Description to be stored.
     * @param by Deadline.
     * @param notes A string representation of notes.
     */
    public Deadline(String description, LocalDateTime by, String notes) {
        this(description, false, by, notes);
    }

    /**
     * Constructor for Deadline.
     *
     * @param description Description to be stored.
     * @param isDone Boolean that represents Deadline completion status.
     * @param by Deadline.
     * @param notes A string representation of notes.
     */
    public Deadline(String description, boolean isDone, LocalDateTime by, String notes) {
        super(description, isDone, notes);
        this.by = by;
    }

    /**
     * Returns a string representation of Deadline.
     *
     * @return A string representation of Deadline.
     */
    @Override
    public String toString() {
        String dateString = by.format(format);
        String str = "[D]" + super.toString() + " (by: " + dateString + ")";
        if (!notes.equals("")) {
            str += "\n  Notes: " + notes;
        }
        return str;
    }

    /**
     * Returns a string formatted for saving purposes.
     *
     * @return A string representation of Deadline for saving.
     */
    @Override
    public String printInSaveFormat() {
        String dateString = by.format(Parser.FORMAT);
        String[] info = {"D", isDone ? "1" : "0", description, dateString, notes};
        return String.join(" | ", info);
    }
}
