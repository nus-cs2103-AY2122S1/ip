package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A task that needs to be completed by a certain DateTime.
 */
public class Deadline extends Task {

    /** Static formatter to format DateTime for display. */
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy hh:mma");

    /** Static formatter to format DateTime for save file. */
    private static final DateTimeFormatter saveFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    /** DateTime of the deadline. */
    private final LocalDateTime by;

    /**
     * Public constructor to create a Deadline object.
     *
     * @param description Description of the deadline.
     * @param by DateTime of the deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the String representation of the deadline.
     *
     * @return String representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    /**
     * Returns the formatted String representation
     * of the deadline to write into the save file.
     *
     * @return Formatted String representation of the deadline.
     */
    @Override
    public String formatSave() {
        return "D%," + isDone + "%," + description + "%," + by.format(saveFormatter);
    }
}
