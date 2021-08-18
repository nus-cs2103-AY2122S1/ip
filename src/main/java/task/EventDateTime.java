package task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The is the EventDateTime class that contains the specific date,
 * start time and end time of event.
 *
 * @author  HU JIAJUN
 * @version %I%, %G%
 * @since   1.0
 */

public class EventDateTime {
    private final LocalDate atDate;
    private final LocalTime startTime;
    private final LocalTime endTime;

    /**
     * This is constructor method of EventDateTime.
     *
     * @param atDate    a specific date of task
     * @param startTime a specific start time of task
     * @param endTime   a specific end time of task
     */
    public EventDateTime(LocalDate atDate, LocalTime startTime, LocalTime endTime) {
        this.atDate = atDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Get the specific date of task.
     *
     * @return the specific date of task
     */
    public LocalDate getAtDate() {
        return atDate;
    }

    /**
     * Get the specific time of task start.
     *
     * @return the specific start time of task
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Get the specific time of task end.
     *
     * @return the specific end time of task
     */
    public LocalTime getEndTime() {
        return endTime;
    }
}
