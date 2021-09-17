package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

/**
 * Encapsulates the Event class which has a starting date and time.
 */
public class Event extends Task {
    private String at;
    private LocalDate eventDate;
    private LocalTime eventTime;

    /**
     * Constructor for new event objects as input by user.
     *
     * @param description Description of the event task.
     * @param at Starting date and time of the event as input by user.
     * @throws DukeException If the date and time are formatted incorrectly.
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            String[] dateTime = at.split(" ");
            this.eventDate = LocalDate.parse(dateTime[0].trim());
            this.eventTime = LocalTime.parse(dateTime[1].trim());
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            throw new DukeException("Event date and time are invalid."
                    + " Please format them as:\n\t[yyyy-mm-dd HH:MM]");
        }
        this.at = at;
    }

    /**
     * Constructor for event objects as read from saved file.
     *
     * @param description Description of the event task.
     * @param isDone Indicates if event is done.
     * @param at Starting date and time of the event as input by user.
     */
    public Event(String description, boolean isDone, String at) throws DukeException {
        super(description, isDone);
        this.at = at;
        String[] dateTime = at.split(" ");
        this.eventDate = LocalDate.parse(dateTime[0].trim());
        this.eventTime = LocalTime.parse(dateTime[1].trim());
    }

    /**
     * Gets the task type indicator to be displayed when the
     * user lists tasks.
     *
     * @return The String representation of the type label for Event.
     */
    @Override
    public String getTypeIndicator() {
        return "[E]";
    }

    /**
     * Converts the Event object to a String representation to be stored
     * in file.
     *
     * @return The String representation of the file record of the Event object.
     */
    @Override
    public String toFileRecord() {
        return String.format("E | %d | %s | %s",
                this.isDone ? 1 : 0 , this.description, this.at);
    }

    /**
     * Returns a String representation of the object.
     *
     * @return The String representation of the Event object.
     */
    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(),
                eventDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy "))
                + eventTime.format(DateTimeFormatter.ofPattern("h:mm a")));
    }
}
