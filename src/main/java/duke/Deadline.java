package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A Task that can be added to the Task List
 * which has a deadline date after the description.
 */

public class Deadline extends Task {


    /**
     * A public constructor to create a Deadline Task.
     * @param description The description of the Deadline.
     * @param byDate The date of the Deadline.
     * @param byTime The time of the Deadline.
     */
    public Deadline(String description, LocalDate byDate, LocalTime byTime) {
        super(description, byDate, byTime);
    }


    /**
     * Returns the string representation of the deadline.
     * @return the string representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + "(By: "
                + this.getStartDate().format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " "
                + this.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm"))
                + ")";
    }


    /**
     * Returns the string representation of the
     * deadline to be saved into the file.
     * @return the string representation of the
     *         deadline to be saved into the file.
     */
    @Override
    public String toStore() {
        return "[D]" + super.toString()
                + "/by "
                + this.getStartDate()
                + " "
                + this.getStartTime();
    }

}
