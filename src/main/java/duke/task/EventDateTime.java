package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * This is the EventDateTime class that contains the specific date,
 * start time and end time of event.
 */
public class EventDateTime {
    private final LocalDate atDate;
    private final LocalTime startTime;
    private final LocalTime endTime;

    /**
     * Constructs a EventDateTime object.
     *
     * @param atDate A specific date of task.
     * @param startTime A specific start time of task.
     * @param endTime A specific end time of task.
     */
    public EventDateTime(LocalDate atDate, LocalTime startTime, LocalTime endTime) {
        this.atDate = atDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns the specific date of task.
     *
     * @return The specific date of task.
     */
    public LocalDate getAtDate() {
        return atDate;
    }

    /**
     * Returns the specific time of task start.
     *
     * @return The specific start time of tasks.
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Returns the specific time of task end.
     *
     * @return The specific end time of task.
     */
    public LocalTime getEndTime() {
        return endTime;
    }
}
