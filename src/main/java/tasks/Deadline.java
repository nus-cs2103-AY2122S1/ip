package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class encapsulates all the details of each deadline.
 *
 * @author Quan Teng Foong
 */
public class Deadline extends Task {
    private final LocalDate endTime;

    /**
     * Constructor for Deadline object.
     *
     * @param message name of Deadline
     * @param endTime deadline time
     */
    public Deadline(String message, String endTime) {
        super(message);
        this.endTime = LocalDate.parse(endTime);
    }

    /**
     * Overrides toString() method.
     * @return String representation of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
    /**
     * Converts contents to a storable String.
     *
     * @return a String that represents this Deadline in storage
     */
    @Override
    public String toStorage() {
        return "D|" + super.toStorage() + "/by " + this.endTime;
    }

    /**
     * Overrides clone method.
     *
     * @return another instance of the exact same Deadline
     */
    @Override
    public Task clone() {
        return new Deadline(this.getMessage(), this.endTime.toString());
    }
}
