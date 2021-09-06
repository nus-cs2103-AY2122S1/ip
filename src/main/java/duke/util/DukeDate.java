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

    private static final DateTimeFormatter OFFICIAL_FORMAT = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Constructor for DukeDate
     * Holds the start time date and end time date of the task
     */
    public DukeDate(LocalDateTime startTime, LocalDateTime endTime) {

        this.startTime = startTime;
        this.endTime = endTime;

    }

    /**
     * Constructor for DukeDate
     * Holds the start time date and end time date of the task and sets the start time
     * to be the current start time date
     */
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
     * Setter that sets the start date and time of the task
     *
     *
     */
    public void setStartTime(LocalDateTime newStartDateTime) {

        this.startTime = newStartDateTime;
    }

    /**
     * Setter that sets the end date and time of the task
     *
     */
    public void setEndTime(LocalDateTime newEndDateTime) {

        this.endTime = newEndDateTime;

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
        String startTimeString = this.startTime.format(OFFICIAL_FORMAT);
        String endTimeString = this.endTime.format(OFFICIAL_FORMAT);
        String finalString = startTimeString + " to " + endTimeString;
        return finalString;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DukeDate) {
            DukeDate temp = (DukeDate) obj;
            boolean startTimeVerification = temp.getStartTime().isEqual(this.startTime);
            boolean endTimeVerification = temp.getEndTime().isEqual(this.endTime);
            return startTimeVerification && endTimeVerification;
        } else {
            return false;
        }
    }

}
