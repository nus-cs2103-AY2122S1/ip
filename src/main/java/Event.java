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

    /**
     * Creates an Event Object.
     *
     * @param toDo name of the event occuring at the specified date.
     * @param date date of the event.
     * @param time time of the event.
     */

    public Event(String toDo, LocalDate date, LocalTime time){
        super(toDo);
        this.date = date;
        this.time = time;
    }

    String getType() {
        return "E";
    }

    String getDateString() {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))  + ", " + 
        time.format(DateTimeFormatter.ofPattern("hh:mm a"));
    }

    /**
     * Returns the Event String that will be written into Storage
     * Date will have the format MMM dd yyyy
     * Time will have the format hh:mm AM/PM
     *
     * @return string with the format [E] | status | event name | date and time of event
     */

    public String getToWrite() {
        return this.getType() + " | " + super.getToWrite() + " | " + this.getDateString();
    }

    @Override
    public String toString() {
        return("[E]" + super.toString() + "(by: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " "
                + time.format(DateTimeFormatter.ofPattern("hh:mm a")) + ")");
    }
}