package models;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

/**
 * Event class that represents a Deadline that will be saved by Dub.
 */
public class Deadline extends Task {

    /** The deadline of the task. */
    private LocalDate time;

    /**
     * Constructor of the Deadline class.
     *
     * @param description Description of the Deadline.
     * @param time Deadline of the task.
     */
    public Deadline(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    /**
     * Equals function that check whether 2 objects are equal or not.
     *
     * @param obj Object that will be compared.
     * @return True if the object are equal.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof Deadline) {
            Deadline temp = (Deadline) obj;
            return temp.toString() == this.toString();
        }

        return false;
    }

    /**
     * Return string implementation of the Deadline object.
     *
     * @return String implementation of the Deadline.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[D][X] " + this.description + " (by: " + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
        return "[D][ ] " + this.description + " (by: " + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}