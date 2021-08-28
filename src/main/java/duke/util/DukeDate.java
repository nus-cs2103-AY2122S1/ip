package duke.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 *
 * Description:
 * Encapsulates the DukeDate Class where it stores the duration/dates
 * for the deadline and event classes
 *
 * @author Keith Tan
 */
public class DukeDate {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private final DateTimeFormatter OFFICIAL_FORMAT = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");

    public DukeDate(LocalDateTime startTime, LocalDateTime endTime) {

        this.startTime = startTime;
        this.endTime = endTime;

    }

    public DukeDate(LocalDateTime endTime) {
        this.endTime = endTime;
        this.startTime = LocalDateTime.now();
    }

    /**
     * Getter that returns the start date and time of the task
     *
     * @return LocalDateTime returns the start date and time of the task
     *
     */
    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    /**
     * Getter that returns the end date and time of the task
     *
     * @return LocalDateTime returns the end date and time of the task
     *
     */
    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    /**
     * Getter that returns the end date and time of the task in a string format
     *
     * @return String returns the end date and time of the task in a string
     *
     */
    public String getEndTimeString() {

        return this.endTime.format(OFFICIAL_FORMAT);

    }

    @Override
    public String toString() {
        return this.startTime.format(this.OFFICIAL_FORMAT) + " to " + this.endTime.format(this.OFFICIAL_FORMAT);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DukeDate) {
            DukeDate temp = (DukeDate) obj;
            return temp.getStartTime().isEqual(this.startTime) && temp.getEndTime().isEqual(this.endTime);
        } else {
            return false;
        }
    }

}