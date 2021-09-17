package model.task;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Class for Deadline with specific time for when task is due.
 *
 * @author Kan Jitpakdi
 * @author GitHub: kanjitp
 * @version 0.03
 * @since 0.01
 */
public class Deadline extends TimeTask {

    /**
     * Adapted constructor for deadline.
     * isDone is set to false.
     *
     * @param description the description of the deadline.
     * @param by          the time of the event with yyyy-MM-dd format.
     */
    public Deadline(String description, String by) {
        super(description, by);
    }

    /**
     * Adapted constructor for deadline.
     *
     * @param description the description of the deadline.
     * @param isDone      whether the deadline is done.
     * @param by          the time of the event with yyyy-MM-dd format.
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone, by);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDescription(), getTime(), this.getClass().getName());
    }

    /**
     * Check whether the given object is the same object, is of type Deadline and have matching description and time.
     *
     * @param other other object to be checked
     * @return whether that object should equal to this deadline or not
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Deadline)) {
            return false;
        }
        Deadline otherDeadline = (Deadline) other;
        return otherDeadline.getDescription().equals(getDescription()) && otherDeadline.getTime().equals(getTime());
    }

    /**
     * Template: "[D][x] description (by: Month DD YYYY)" or "[D][ ] description (by: Month DD YYYY)" for done
     * and not done task respectively.
     *
     * @return the template above for Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
                + this.getTime().format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
