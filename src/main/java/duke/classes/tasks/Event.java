package duke.classes.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Event Tasks
 */
public class Event extends Task {
    protected LocalDate eventDate;
    protected LocalTime eventTime;
    private DateTimeFormatter printOut = DateTimeFormatter.ofPattern("MMM dd, E, yyyy");
    private DateTimeFormatter printTimeOut = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * Class Constructor
     *
     * @param description Description of the Event
     * @param eventDate Time of the event
     */
    public Event(String description, LocalDate eventDate) {
        super(description, Task.Type.E);
        this.eventDate = eventDate;
    }

    /**
     * Class Constructor
     *
     * @param description Description of the Event
     * @param eventDate Time of the event
     */
    public Event(String description, LocalDate eventDate, LocalTime eventTime) {
        super(description, Task.Type.E);
        this.eventDate = eventDate;
        this.eventTime = eventTime;
    }

    /**
     * Class Constructor when Event is read from localList.txt
     *
     * @param description Description of the Event
     * @param eventDate Date of the event
     * @param isDone Checks if the event is done
     */
    public Event(String description, String eventDate, boolean isDone) {
        super(description, Task.Type.E, isDone);
        this.eventDate = LocalDate.parse(eventDate);
    }

    /**
     * Class Constructor when Event is read from localList.txt
     *
     * @param description Description of the Event
     * @param eventDate Date of the event
     * @param eventTime Time of the event
     * @param isDone Checks if the event is done
     */
    public Event(String description, String eventDate, String eventTime, boolean isDone) {
        super(description, Task.Type.E, isDone);
        this.eventDate = LocalDate.parse(eventDate);
        this.eventTime = LocalTime.parse(eventTime);
    }

    @Override
    public String toString() {
        String temp = "";
        if (eventTime != null) {
            temp = " " + printTimeOut.format(eventTime);
        }
        return super.toString() + " (at: " + printOut.format(eventDate) + temp + ")";
    }

    /**
     * Method Returns class as a string in accordance to DukeStorage Format
     *
     * @return String in accordance to DukeStorage Format
     */
    public String toFileString() {
        String temp = "";
        if (eventTime != null) {
            temp = " " + eventTime + " T";
        }
        return super.toFileString() + " " + eventDate.toString() + temp;
    }
}
