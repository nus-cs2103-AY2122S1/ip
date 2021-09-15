package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class that represents a Deadline that will be saved by Dub.
 */
public class Deadline extends Task {

    /** The deadline of the task. */
    private final LocalDate time;

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
     * Returns a copy of the current task.
     *
     * @return A copy of the task.
     */
    public Deadline copyTask() {
        Deadline task = new Deadline(description, time);
        task.isDone = isDone;
        return task;
    }

    /**
     * Returns true if two objects are equal.
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
            return temp.toString().equals(this.toString());
        }

        return false;
    }

    /**
     * Returns string implementation of the Deadline object.
     *
     * @return String implementation of the Deadline.
     */
    @Override
    public String toString() {
        String timeString = time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        if (this.isDone) {
            return "[D][X] " + this.description + " (by: "
                    + timeString + ")";
        }
        return "[D][ ] " + this.description + " (by: "
                + timeString + ")";
    }
}
