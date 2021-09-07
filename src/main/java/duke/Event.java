package duke;

import duke.exception.InvalidEventException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A subclass of Task that represents an Event.
 *
 */
public class Event extends Task {
    String type;
    LocalDate eventDate;
    LocalTime eventStartTime;
    LocalTime eventEndTime;

    /**
     * Constructor for Deadline class.
     *
     * @param title Name of the task to be created.
     * @param eventTime Date and time of event.
     *
     */
    public Event(String title, String eventTime) throws InvalidEventException {
        super(title);
        type = "E";

        String[] dateTime = eventTime.split(" ");
        if (dateTime.length < 1) {
            throw new InvalidEventException();
        }

        try {
            eventDate = LocalDate.parse(dateTime[0]);
            if (dateTime.length == 1) {
                eventStartTime = LocalTime.parse("23:59");
                eventEndTime = LocalTime.parse("23:59");
            }
            String time = dateTime[1];
            eventStartTime = LocalTime.parse(time.split("-")[0]);
            eventEndTime = LocalTime.parse(time.split("-")[1]);
            if (eventStartTime.isAfter(eventEndTime)) {
                throw new InvalidEventException("EventStartTime cannot be later than EventEndTime!");
            }

        } catch (DateTimeParseException e) {
            throw new InvalidEventException();
        }
    }

    @Override
    public String toString() {
        String date = this.eventDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String start = this.eventStartTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        String end = this.eventEndTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        String time = date + " " + start + "-" + end;
        return "[" + type + "]" + super.toString() + "(at: " + time + ")";
    }

    /**
     * A method that prints out details of an event.
     *
     * @return Details of an event.
     */
    @Override
    public String writeTask() {
        String date = this.eventDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String start = this.eventStartTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        String end = this.eventEndTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        String time = date + " " + start + "-" + end;
        return type + " | " + super.writeTask() + " | " + time;
    }
}