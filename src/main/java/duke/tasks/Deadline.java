package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class inherits from Task that contains the name, the time and the completion status.
 */
public class Deadline extends Task {

    private String marker = "D";
    private LocalDateTime deadline;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");


    /**
     * Constructor for an Deadline.
     *
     * @param name Name of the Deadline.
     * @param deadline Time of the Deadline
     * @param isDone Completion status of the Deadline.
     */
    public Deadline(String name, LocalDateTime deadline, boolean isDone) {
        super(name, isDone);
        this.deadline = deadline;
    }

    /**
     * Returns the time in a specified format.
     *
     * @return Time in dd/MM/yy HHmm.
     */
    @Override
    public String getTime() {
        return this.deadline.format(formatter);
    }

    /**
     * Returns the marker "D" denoting deadline.
     *
     * @return A string "D".
     */
    @Override
    public String getMarker() {
        return this.marker;
    }

    /**
     * Returns the string representation of an Event.
     *
     * @return Event in string.
     */
    @Override
    public String toString() {
        return "[" + this.marker + "]" + super.toString()
                + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy EEE HH:mm")) + ")";
    }
}
