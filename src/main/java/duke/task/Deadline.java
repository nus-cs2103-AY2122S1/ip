package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that the user wants to complete by a certain point in time.
 */
public class Deadline extends Task {
    protected boolean hasTime;
    protected LocalDateTime dateTime;
    protected LocalDate date;
    protected DateTimeFormatter formatInput;
    protected DateTimeFormatter formatOutputTime = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
    protected DateTimeFormatter formatOutputNoTime = DateTimeFormatter.ofPattern("MMM d yyyy");

    /**
     * Constructs a Deadline object.
     *
     * @param description Description of the task.
     * @param dateString  Date and time of the deadline.
     * @param formatInput How the input is formatted.
     * @param hasTime     Boolean for whether time was supplied.
     */
    public Deadline(String description, String dateString, DateTimeFormatter formatInput, boolean hasTime,
                    boolean isDone) {
        super(description, isDone);
        this.formatInput = formatInput;
        this.hasTime = hasTime;
        if (!hasTime) {
            this.date = LocalDate.parse(dateString, formatInput);
        } else {
            this.dateTime = LocalDateTime.parse(dateString, formatInput);
        }
    }

    /**
     * Provides a String representation of the duke.task.Deadline.
     *
     * @return A String representation of the duke.task.Deadline.
     */
    @Override
    public String toString() {
        if (this.hasTime) {
            return "[D][" + this.getStatusIcon() + "] " + this.description + " (by: " +
                    formatOutputTime.format(this.dateTime) + ")";
        } else {
            return "[D][" + this.getStatusIcon() + "] " + this.description + " (by: " +
                    formatOutputNoTime.format(this.date) + ")";
        }
    }

    /**
     * Increases the date of the task by a set number of days.
     *
     * @param days Number of days to increase by.
     */
    public void increaseDateByDays(int days) {
        if (this.hasTime) {
            this.dateTime = this.dateTime.plusDays(days);
        } else {
            this.date = this.date.plusDays(days);
        }
    }
}
