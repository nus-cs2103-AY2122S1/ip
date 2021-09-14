package jarvis.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import jarvis.exception.InvalidDateTimeInputException;

/**
 * Encapsulates the event task which contains a description and event time.
 */
public class Event extends Task {
    private static final String INPUT_DATE_FORMAT = "dd-MM-yyyy";
    private static final String INPUT_TIME_FORMAT = "HHmm";
    private static final String OUTPUT_DATE_FORMAT = "MMM d yyyy";
    private static final String OUTPUT_TIME_FORMAT = "h:mm a";
    private LocalDate eventDate;
    private LocalTime eventStartTime;
    private LocalTime eventEndTime;

    /**
     * Constructor for Event.
     *
     * @param description The description for the event.
     * @param eventDate The event date.
     * @param eventStartTime The event start time.
     * @param eventEndTime The event end time.
     * @throws InvalidDateTimeInputException If there is an error parsing the date and time.
     */
    public Event(String description, String eventDate, String eventStartTime, String eventEndTime)
            throws InvalidDateTimeInputException {
        super(description);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(INPUT_TIME_FORMAT);
        try {
            this.eventDate = LocalDate.parse(eventDate, dateFormatter);
            this.eventStartTime = LocalTime.parse(eventStartTime, timeFormatter);
            this.eventEndTime = LocalTime.parse(eventEndTime, timeFormatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeInputException("event",
                    String.format("%s %s %s", INPUT_DATE_FORMAT, INPUT_TIME_FORMAT, INPUT_TIME_FORMAT)
            );
        }
    }

    /**
     * Gets the string representation of an event task.
     *
     * @return String representation of an event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(OUTPUT_TIME_FORMAT);
        String formattedEventTime = String.format(
                "%s %s to %s",
                eventDate.format(dateFormatter),
                eventStartTime.format(timeFormatter),
                eventEndTime.format(timeFormatter)
        );
        return String.format("[E]%s (at: %s)", super.toString(), formattedEventTime);
    }

    /**
     * Gets the string representation of an event task that is to be saved to storage file.
     *
     * @return String representation that is to be saved.
     */
    @Override
    public String toStorageFormatString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(INPUT_TIME_FORMAT);
        return String.format(
                "%s;;;%s;;;%s;;;%s;;;%s",
                "E",
                super.toStorageFormatString(),
                eventDate.format(dateFormatter),
                eventStartTime.format(timeFormatter),
                eventEndTime.format(timeFormatter)
        );
    }
}
