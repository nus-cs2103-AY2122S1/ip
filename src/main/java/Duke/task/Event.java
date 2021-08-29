package duke.task;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task. An event task has a start and end time.
 */
public class Event extends Task {

    private String timingStr;
    private LocalDateTime timing;
    private LocalTime endTime;

    public Event(String details, String timing) {
        super(details);
        if (timing.length() == 20) {
            try {
                this.timing = LocalDateTime.parse(timing.substring(0, 15),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                endTime = LocalTime.parse(timing.substring(16),
                        DateTimeFormatter.ofPattern("HHmm"));
            } catch (DateTimeParseException e) {
                timingStr = timing;
                this.timing = null;
                this.endTime = null;
            }
        } else {
            timingStr = timing;
            this.timing = null;
            this.endTime = null;
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
     * Most likely due to incorrect input format.
     *
     * @return Task timing as a String.
     */
    public String getTimingStr() {
        return timingStr;
    }

    /**
     * Returns a String representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        if (timing == null || endTime == null) {
            return "[E]" + super.toString() + " (at: " + timingStr + ")";
        } else {
            return "[E]" + super.toString() + " (at: "
                    + timing.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a"))
                    + " to " + endTime.format(DateTimeFormatter.ofPattern("hh:mm a")) + ")";
        }
    }
}
