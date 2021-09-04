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
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("hh:mm a");
    private String timingWrongFormat; // Used to store incorrect timing formats.
    private LocalDateTime timing;
    private LocalTime endTime;

    /**
     * Constructor.
     *
     * @param details Details of the event task.
     * @param timing Timing of the event task.
     */
    public Event(String details, String timing) {
        super(LABEL, details);
        if (timing.length() == 20) {
            try {
                this.timing = LocalDateTime.parse(timing.substring(0, 15),
                        DATE_TIME_FORMATTER);
                endTime = LocalTime.parse(timing.substring(16),
                        TIME_FORMATTER);
            } catch (DateTimeParseException e) {
                timingWrongFormat = timing;
                this.timing = null;
                endTime = null;
            }
        } else {
            timingWrongFormat = timing;
            this.timing = null;
            endTime = null;
        }
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
     * Returns timing of task as a String.
     *
     * @return Task timing as a String.
     */
    public String getTimingAsStr() {
        if (timing == null) {
            return timingWrongFormat;
        } else {
            return timing.format(DATE_TIME_FORMATTER)
                    + "-" + endTime.format(TIME_FORMATTER);
        }
    }

    /**
     * Returns a String representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + LABEL + "]" + super.toString() + " (at: " + getTimingAsStr() + ")";
    }
}
