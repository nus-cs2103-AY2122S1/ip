package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import duke.logic.DateTimeParser;

/**
 * An event is a task that has a date attached.
 */
public class Event extends Task {
    private final LocalDate startDateOfEvent;
    private final LocalTime startTimeOfEvent;
    private final LocalDate endDateOfEvent;
    private final LocalTime endTimeOfEvent;

    /**
     * Creates a new event with the given description on the given eventDate.
     *
     * @param description        the description of the event
     * @param startEventDateTime the starting date and time of the event
     * @param endEventDateTime   the ending date and time of the event
     */
    public Event(String description, String startEventDateTime, String endEventDateTime) {
        super(description, "E");
        DateTimeParser logicDateTimeParser = new DateTimeParser(startEventDateTime); // to parse start
        startTimeOfEvent = logicDateTimeParser.getTime();
        startDateOfEvent = logicDateTimeParser.getDate();

        if (endEventDateTime == null
            || endEventDateTime.trim().isEmpty()) {
            endDateOfEvent = startDateOfEvent;
            endTimeOfEvent = startTimeOfEvent;
        } else {
            logicDateTimeParser = new DateTimeParser(endEventDateTime,
                startDateOfEvent, startTimeOfEvent); // to parse the end
            endDateOfEvent = logicDateTimeParser.getDate();
            endTimeOfEvent = logicDateTimeParser.getTime();
        }
    }

    /**
     * Creates a new event object that has the given description and due on the given date.
     *
     * @param description   the description of the event
     * @param startDateTime the start date/time of the event
     * @param endDateTime   the end date/time of the event
     */
    public Event(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description, "E");
        startDateOfEvent = startDateTime.toLocalDate();
        startTimeOfEvent = startDateTime.toLocalTime();
        endDateOfEvent = endDateTime.toLocalDate();
        endTimeOfEvent = endDateTime.toLocalTime();
    }

    /**
     * Gets the date and time of the task, or null if not applicable.
     * This is by default, the start date and time of the event.
     *
     * @return the associated LocalDateTime object
     */
    @Override
    public LocalDateTime getDateTime() {
        return LocalDateTime.of(startDateOfEvent, startTimeOfEvent);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (from: %s, %s to: %s, %s)",
            startDateOfEvent, startTimeOfEvent, endDateOfEvent, endTimeOfEvent); // No preposition
    }

    @Override
    public String getDataLine() {
        return super.getDataLine() + " | " + startDateOfEvent + " "
            + startTimeOfEvent + " | " + endDateOfEvent + " " + endTimeOfEvent;
    }
}
