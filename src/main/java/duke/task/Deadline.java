package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates the idea of tasks that need to be done by a certain date and time
 */
public class Deadline extends Task {

    // constants
    private static final String TYPE = "D";
    private static final DateTimeFormatter DATABASE_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    // instance variables
    private LocalDateTime deadline;

    // constructors

    /**
     * Constructs a Deadline object without a date
     *
     * @param description the description of the Deadline
     */
    public Deadline(String description) {
        super(description);
    }

    /**
     * Constructs a Deadline object with the given due date
     *
     * @param description the description of the Deadline
     * @param due         the date of the deadline
     */
    public Deadline(String description, String due) {
        super(description);
        this.deadline = LocalDateTime.parse(due, DATABASE_DATE_TIME_FORMAT);
    }

    /**
     * Constructs a Deadline object with the given due date and whether it is completed
     *
     * @param description the description of the Deadline
     * @param deadline    the date of the deadline
     * @param isDone      whether the task is completed
     */
    public Deadline(String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = LocalDateTime.parse(deadline, DATABASE_DATE_TIME_FORMAT);
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
     * @return the type of Deadline
     */
    public String getLabel() {
        return TYPE;
    }

    /**
     * A method to convert and format a Deadline object
     * into a string to be stored in the database
     *
     * @return the formatted string representation
     */
    @Override
    public String databaseString() {
        return TYPE + " | " + super.databaseString() + " | "
                + formatDate(deadline)[0] + " " + formatDate(deadline)[1];
    }

    /**
     * A method to convert a Deadline object into a string representation
     *
     * @return the string representation of a Deadline
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)", getLabel(), getStatusIcon(),
                this.description, formatDate(deadline)[0] + " " + formatDate(deadline)[1]);
    }
}
