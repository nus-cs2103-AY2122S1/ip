package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates the idea of tasks that happens at a certain date and time
 */
public class Event extends Task {

    // constants
    private static final String TYPE = "E";
    private static final DateTimeFormatter DATABASE_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    // instance variables
    private LocalDateTime date;

    // constructors


    /**
     * Constructs an Event object without a date
     *
     * @param description the description of the Event
     */
    public Event(String description) {
        super(description);
    }

    /**
     * Constructs an Event object with the given due date
     *
     * @param description the description of the Event
     * @param date        the date of the Event
     */
    public Event(String description, String date) {
        super(description);
        this.date = LocalDateTime.parse(date, DATABASE_DATE_TIME_FORMAT);
    }

    /**
     * Constructs an Event object with the given due date and whether it is completed
     *
     * @param description the description of the Event
     * @param date        the date of the Event
     * @param isDone      whether the Event is completed
     */
    public Event(String description, String date, boolean isDone) {
        super(description, isDone);
        this.date = LocalDateTime.parse(date, DATABASE_DATE_TIME_FORMAT);
    }

    /**
     * Helper method to format the date according to the
     * given format and split it to date and time
     *
     * @param date the date to be formatted
     * @return the formatted date and time
     */
    private String[] formatDate(LocalDateTime date) {
        return date.format(DATABASE_DATE_TIME_FORMAT).split(" ");
    }

    /**
     * Getter method to get the String representation
     * of the type of the Deadline object
     *
     * @return the type of Event
     */
    public String getLabel() {
        return TYPE;
    }

    /**
     * A method to convert and format an Event object
     * into a string to be stored in the database
     *
     * @return the formatted string representation
     */
    @Override
    public String databaseString() {
        return TYPE + " | " + super.databaseString() + " | "
                + formatDate(date)[0] + " " + formatDate(date)[1];
    }

    /**
     * A method to convert an Event object into a string representation
     *
     * @return the string representation of an Event
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (at: %s)", getLabel(), getStatusIcon(),
                this.description, formatDate(date)[0] + " " + formatDate(date)[1]);
    }
}
