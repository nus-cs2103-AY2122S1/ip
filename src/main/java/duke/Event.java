package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A Task that can be added to the Task List
 * which has more details after the description.
 */
public class Event extends Task {


    /**
     * A public constructor to create an Event Task with End Date and Time specified.
     * @param description The description of the Event.
     * @param startDate The start date of the Event.
     * @param startTime The start time of the Event.
     * @param endDate The end date of the Event.
     * @param endTime The end time of the Event.
     */
    public Event(String description, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        super(description, startDate, startTime, endDate, endTime);

    }


    /**
     * Returns the string representation of the event.
     * @return the string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + "(Start: "
                + this.getStartDate().format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " "
                + this.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm"))
                + " End: "
                + this.getEndDate().format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " "
                + this.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm"))
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
                + this.getStartDate()
                + " "
                + this.getStartTime()
                + " "
                + this.getEndDate()
                + " "
                + this.getEndTime();
    }

}