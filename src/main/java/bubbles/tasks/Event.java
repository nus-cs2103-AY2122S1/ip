package bubbles.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A child class of Task, representing the tasks that start at
 * a specific time.
 */
public class Event extends Task {
    private LocalDate eventTime;

    private Event(String description, boolean completed, String eventTime) {
        super(description, completed);
        this.eventTime = Task.formatDate(eventTime);
    }

    /**
     * Act as a public constructor of an Event Object.
     *
     * @param input The description of the Event, including the Event date
     * @param completed Whether the Event is done/completed
     * @return The created Event Object
     */
    public static Event addEvent(String input, boolean completed) {
        String[] arr = input.split(" /at ");

        Event item = new Event(arr[0], completed, arr[1]);

        return item;
    }

    /**
     * Format the Event Object to store in the hard disk File.
     *
     * @return String representing the Event Object (different from the String representation
     *          of the Event Task)
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
     * Return the String representation of the Event.
     *
     * @return The String representation of the Event.
     */
    @Override
    public String toString() {
        String date = this.eventTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String time = "(at: " + date + ")";

        String res = "[E] [" + this.getStatus() + "] " + this.description + " " + time;

        return res;
    }
}