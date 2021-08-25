package duke.task;

import duke.logic.LDateTimeParser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * An event is a task that has a date attached.
 */
public class Event extends Task {
    private final LocalDate dateOfEvent;
    private final LocalTime timeOfEvent;

    /**
     * Creates a new event with the given description on the given eventDate.
     *
     * @param description The description of the event
     * @param eventDate   The date of the event
     */
    public Event(String description, String eventDate) {
        super(description, "E");
        LDateTimeParser logicDateTimeParser = new LDateTimeParser(eventDate);
        timeOfEvent = logicDateTimeParser.getTime();
        dateOfEvent = logicDateTimeParser.getDate();
    }

    @Override
    public LocalDateTime getDateTime() {
        return LocalDateTime.of(dateOfEvent, timeOfEvent);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (at: %s, %s)", dateOfEvent, timeOfEvent); // No preposition
    }

    @Override
    public String getDataLine() {
        return super.getDataLine() + " | " + dateOfEvent + " " + timeOfEvent;
    }
}
