package bubbles.tasks;

import bubbles.exceptions.EmptyTaskException;
import bubbles.exceptions.InvalidFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A child class of Task, representing the tasks that start at
 * a specific time.
 */
public class Event extends Task {
    private LocalDate eventTime;

    private Event(String description, boolean isDone, String eventTime) throws InvalidFormatException {
        super(description, isDone);

        try {
            this.eventTime = Task.formatDate(eventTime);
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException("The date you've entered should be in the format of yyyy-mm-dd.");
        }
    }

    /**
     * Acts as a public constructor of an Event Object.
     *
     * @param input The description of the Event, including the Event date.
     * @param isDone Whether the Event is done/completed.
     * @return The created Event Object.
     */
    public static Event addEvent(String input, boolean isDone) throws EmptyTaskException, InvalidFormatException {
        if (input.equals("")) {
            throw new EmptyTaskException("event");
        }

        String[] arr = input.split(" /at ");

        Event item;

        try {
            item = new Event(arr[0], isDone, arr[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidFormatException("When adding an event to the task list, the date field cannot be empty.");
        }


        return item;
    }

    /**
     * Formats the Event Object to store in the hard disk File.
     *
     * @return String representing the Event Object (different from the String representation
     *          of the Event Task).
     */
    @Override
    public String format() {
        String format = "E | ";

        if (this.isDone) {
            format += "1 | ";
        } else {
            format += "0 | ";
        }

        format += this.description;
        format += " | at ";
        format += this.eventTime;

        return format;
    }

    /**
     * Gets the event time of this event and returns that to the user.
     *
     * @return The event time of the event, eg. 2021-09-09
     */
    @Override
    public LocalDate getDate() {
        return this.eventTime;
    }

    /**
     * Returns the String representation of the Event.
     *
     * @return The String representation of the Event.
     */
    @Override
    public String toString() {
        String date = this.eventTime.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        String time = "(at: " + date + ")";
        String res = "[E] [" + this.getStatus() + "] " + this.description + " " + time;

        return res;
    }
}
