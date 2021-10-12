package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A task that occurs at a certain DateTime.
 */
public class Event extends Task {

    /** Static formatter to format DateTime for display. */
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy hh:mma");

    /** Static formatter to format DateTime for save file. */
    private static final DateTimeFormatter saveFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    /** DateTime of the event. */
    private final LocalDateTime at;

    /**
     * Public constructor to create an Event object.
     *
     * @param description Description of the event.
     * @param at DateTime of the event.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the String representation of the event.
     *
     * @return String representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(formatter) + ")";
    }

    /**
     * Returns the formatted String representation
     * of the event to write into the save file.
     *
     * @return Formatted String representation of the event.
     */
    @Override
    public String formatSave() {
        return "E%," + isDone + "%," + description + "%," + at.format(saveFormatter);
    }
}
