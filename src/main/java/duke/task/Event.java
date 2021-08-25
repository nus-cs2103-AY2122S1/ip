package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class creates event instances which are to be done at a certain time.
 */
public class Event extends Task {
    // The date of the event.
    protected LocalDate date;
    // The time of the date to complete the event by.
    protected String time = "";

    /**
     * Constructor to create a event.
     *
     * @param name The name of the event.
     * @param date The date of the event.
     */
    public Event(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    /**
     * Secondary constructor to which includes time.
     *
     * @param name The name of the event.
     * @param date The date of the event.
     * @param time The time of the event.
     */
    public Event(String name, LocalDate date, String time) {
        super(name);
        this.date = date;
        this.time = time;
    }

    /**
     * Returns the string representation of the event.
     *
     * @return The name of the event.
     */
    @Override
    public String toString() {
        String timeComponent = (this.time.equals("")
                ? this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                : this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " " + this.time);
        return "[E]" + super.toString() + " (at: "
                + timeComponent
                + ")";
    }
}