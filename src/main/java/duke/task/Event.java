package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *  Represents an Event task.
 *  An Event consists of a date and time representing the date and time of the Event.
 */
public class Event extends Task {

    private LocalDate date;
    private LocalTime time;
    private String frequency;
    private boolean isUpdated;


    /**
     * Creates an Event Object.
     *
     * @param toDo name of the event occuring at the specified date.
     * @param date date of the event.
     * @param time time of the event.
     */
    public Event(String toDo, LocalDate date, LocalTime time, String frequency) {
        this(toDo, date, time, frequency, false);
    }

    public Event(String toDo, LocalDate date, LocalTime time, String frequency, boolean isUpdated) {
        super(toDo);
        this.date = date;
        this.time = time;
        this.frequency = frequency;
        this.isUpdated = isUpdated;
    }

    String getType() {
        return "E";
    }

    String getDateString() {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + ", "
                + time.format(DateTimeFormatter.ofPattern("hh:mm a")
        );
    }

    /**
     * checks if Event Date was recently updated. Only updates isDone if Event Date was not recently updated, then
     * resets isUpdated
     */
    @Override
    public void complete() {
        if (!this.isUpdated) {
            super.complete();
        }
        this.isUpdated = false;
    }

    /**
     * Returns the Event String that will be written into Storage
     * Date will have the format MMM dd yyyy
     * Time will have the format hh:mm AM/PM
     *
     * @return string with the format [E] | status | event name | date and time of event
     */
    public String getToWrite() {
        return this.getType() + " | " + super.getToWrite() + " | " + this.getDateString() + " | " + frequency;
    }

    /**
     * Returns the Event String
     * Date will have the format MMM dd yyyy
     * Time will have the format hh:mm AM/PM
     *
     * @return string with the format "[D] status and task (at: date) frequency
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " "
                + time.format(DateTimeFormatter.ofPattern("hh:mm a"))
                + ") "
                + frequency;
    }
}