package Duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A Task that can be added to the Task List
 * which has more details after the description.
 */
public class Event extends Task {

    protected LocalDate startDate;
    protected LocalDate endDate;
    protected LocalTime startTime;
    protected LocalTime endTime;

    /**
     * A public constructor to create an Event Task with no End Date and Time specified.
     * @param description The description of the Event.
     * @param startDate The start date of the Event.
     * @param startTime The start time of the Event.
     */
    public Event(String description, LocalDate startDate, LocalTime startTime) {
        super(description);
        this.startDate = startDate;
        this.startTime = startTime;
    }

    /**
     * A public constructor to create an Event Task with End Date and Time specified.
     * @param description The description of the Event.
     * @param startDate The start date of the Event.
     * @param startTime The start time of the Event.
     * @param endDate The end date of the Event.
     * @param endTime The end time of the Event.
     */
    public Event(String description, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns the string representation of the event.
     * @return the string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + "(Start: "
                + startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " "
                + startTime.format(DateTimeFormatter.ofPattern("HH:mm"))
                + " End: "
                + endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " "
                + endTime.format(DateTimeFormatter.ofPattern("HH:mm"))
                + ")";
    }

    /**
     * Returns the string representation of the
     * event to be saved into the file.
     * @return the string representation of the
     *         event to be saved into the file.
     */
    @Override
    public String toStore() {
        return "[E]" + super.toString()
                + "/at "
                + startDate
                + " "
                + startTime
                + " "
                + endDate
                + " "
                + endTime;
    }
}