package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents tasks of type events, inherit from Task abstract class.
 * Events have a description, date, start time and end time.
 */
public class Events extends Task {
    protected LocalDate date;
    protected LocalTime startTime;
    protected LocalTime endTime;

    /**
     * Constructor of the Events class.
     *
     * @param description description of an event in String.
     * @param date date when the event happens.
     *                  Must be in the format yyyy-mm-dd.
     * @param startTime time point when the event starts.
     *                       Must be in the format hh:mm in 24-hours time.
     * @param endTime time point when the event ends.
     *                     Must be in the format hh:mm in 24-hours time.
     */
    public Events(String description, LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(description);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns the event in a string for print.
     *
     * @return Event represented in a string for print,
     * in the format: [E] [state] description (at date start_time end_time)
     */
    @Override
    public String toString() {
        return String.format("[E] [%s] " + this.description + " (at: %s %s-%s)",
            this.getStatusIcon(),
            this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
            this.startTime,
            this.endTime);
    }

    /**
     * Returns the event in a string for sending to the data file to save.
     *
     * @return Event represented in a string for saving in data file,
     * in the format: D|state|description|date|start_time|end_time
     */
    @Override
    public String toDataFileString() {
        return String.format("E|%s|%s|%s|%s|%s",
            this.isDone ? "1" : "0",
            this.description,
            this.date,
            this.startTime,
            this.endTime);
    }

    /**
     * Sets the date of event.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Sets the start time of event.
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Sets the start time of event.
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}
