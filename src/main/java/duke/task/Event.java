package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * The type Event that has a task description and a date the event occurs at.
 */
public class Event extends Task {

    /** Identifying tag 'E' for Event task */
    private final String identifier = "E";
    /** Stores Date specified by user */
    private final LocalDate eventDate;
    /** Stores Date and Time if both are specified by user */
    private LocalDateTime eventDateTime;
    /** Boolean flag to check if both Date and Time are specified by user */
    private boolean isDateAndTime;
    /** Stores Time specified by use */
    private LocalTime eventTime;

    /**
     * Instantiates a new Event.
     *
     * @param description the description for event task.
     * @param eventDate   the event date.
     * @throws DateTimeParseException exception thrown if user inputs an invalid date.
     */
    public Event(String description, String eventDate) throws DateTimeParseException {
        super(description);

        assert eventDate != null : "Event date cannot be null.";

        this.eventDate = LocalDate.parse(eventDate);
    }

    /**
     * Instantiates a new Event.
     *
     * @param description the description for event task.
     * @param eventDate   the event date.
     * @param eventTime   the event time.
     * @throws DateTimeParseException exception thrown if user inputs an invalid date and/or time.
     */
    public Event(String description, String eventDate, String eventTime) throws DateTimeParseException {
        super(description);

        assert eventDate != null : "Event date cannot be null.";
        assert eventTime != null : "Event date cannot be null.";

        this.eventDate = LocalDate.parse(eventDate);
        this.eventTime = LocalTime.parse(eventTime);
        isDateAndTime = true;
        this.eventDateTime = LocalDateTime.of(this.eventDate, this.eventTime);
    }

    /**
     * Prints out Event task with an identifier, a done marker and the date/time specified.
     *
     * @return String.
     */
    @Override
    public String toString() {
        String result = "[" + identifier + "]";
        result += super.toString();
        result += isDateAndTime
                ? " (at: " + this.eventDateTime.format(super.dateTimePattern) + ")"
                : " (at: " + this.eventDate.format(super.datePattern) + ")";
        return result;
    }

    @Override
    public String toDatabaseString() {
        String result = identifier + "|";
        result += getStatus() ? "1|" : "0|";
        result += getDescription() + "|";
        result += isDateAndTime ? this.eventDate + "|" + this.eventTime : this.eventDate;
        return result;
    }
}
