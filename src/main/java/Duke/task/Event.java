package duke.task;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task. An event task has a start and end time.
 */
public class Event extends Task {

    private static final char LABEL = 'E';
    private static final DateTimeFormatter INPUT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter INPUT_TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmm");
    private static final DateTimeFormatter OUTPUT_DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern("yyyy-MM-dd hh:mm a");
    private static final DateTimeFormatter OUTPUT_TIME_FORMATTER = DateTimeFormatter.ofPattern("hh:mm a");
    private final LocalDateTime timing;
    private final LocalTime endTime;

    /**
     * Constructor.
     *
     * @param details Details of the event task.
     * @param timing Timing of the event task.
     */
    public Event(String details, String timing) throws DateTimeParseException {
        super(LABEL, details);
        this.timing = LocalDateTime.parse(timing.substring(0, 15),
                INPUT_DATE_TIME_FORMATTER);
        endTime = LocalTime.parse(timing.substring(16),
                INPUT_TIME_FORMATTER);
    }

    /**
     * Returns timing of task.
     *
     * @return Task timing.
     */
    public LocalDateTime getTiming() {
        return timing;
    }

    /**
     * Returns date of task.
     *
     * @return Task timing.
     */
    @Override
    public LocalDateTime getDate() {
        return getTiming();
    }

    /**
     * Returns end time of task.
     *
     * @return Task end time.
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Returns a String representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + LABEL + "]" + super.toString() + " (at: "
                + timing.format(OUTPUT_DATE_TIME_FORMATTER) + "-" + endTime.format(OUTPUT_TIME_FORMATTER)
                + ")";
    }
}
