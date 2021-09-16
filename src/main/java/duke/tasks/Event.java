package duke.tasks;

import duke.dukeException.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An Event task that extends from Task.
 *
 * @author Erwin Quek
 * @version CS2103 AY21/22 Sem 1
 */
public class Event extends Task{
    private String dateTime;
    private final LocalDateTime dt;

    /**
     * A constructor for Event
     * @param description Takes in a user input
     */
    public Event(String description) throws DukeException {
        super(description.substring(0, description.indexOf("/at ")).trim());
        this.dateTime = description.substring(description.indexOf("/at ") + 4).trim();
        if (dateTime.length() < 16) {
            String message = "\nOOPS!!! Please follow this format: \n event {task} /at {YYYY-MM-DDTHH:MM}";
            throw new DukeException(message);
        }
        this.dt = LocalDateTime.parse(this.dateTime);
    }

    /**
     * Returns a string representation of Event task details to be saved in duke.txt.
     * @return A string representation of Event task.
     */
    @Override
    public String getTaskInfo() {
        return "E" + "|" + super.getZeroOrOne() + "|" + description + "|" + dateTime;
    }

    /**
     * Return a String representation of Event task.
     * @return A string representation of of the Event task.
     */
    @Override
    public String toString() {
        String formatDate = dt.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String formatTime = dt.format(DateTimeFormatter.ofPattern("hh:mm"));
        return "[E]" + super.toString() + " (at: " + formatDate +  ", " + formatTime + ")";
    }
}