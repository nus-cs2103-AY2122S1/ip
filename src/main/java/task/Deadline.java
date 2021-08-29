package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for Deadline with specific time for when task is due
 *
 * @author Kan Jitpakdi
 * @author GitHub: kanjitp
 * @version 0.01
 * @since 0.00
 *
 */
public class Deadline extends TimeTask {

    /**
     * Default constructor for deadline.
     *
     * isDone is set to false and time is set to null.
     *
     * @param description the description of the deadline
     */
    public Deadline(String description) {
        super(description);
    }

    /**
     * Default constructor for deadline.
     *
     * time is set to null.
     *
     * @param description the description of the deadline
     * @param isDone whether the deadline is done
     */
    public Deadline(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Default constructor for deadline.
     *
     * @param description the description of the deadline
     * @param isDone whether the deadline is done
     * @param time the LocalDate time object of the deadline
     */
    public Deadline(String description, boolean isDone, LocalDate time) {
        super(description, isDone, time);
    }

    /**
     * Adapted constructor for deadline.
     *
     * isDone is set to false.
     *
     * @param description the description of the deadline.
     * @param by the time of the event with yyyy-MM-dd format
     */
    public Deadline(String description, String by) {
        super(description, by);
    }

    /**
     * Adapted constructor for deadline.
     *
     * @param description the description of the deadline
     * @param isDone whether the deadline is done
     * @param by the time of the event with yyyy-MM-dd format
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone, by);
    }

    /**
     * Template: "[D][x] description (by: Month DD YYYY)" or "[D][ ] description (by: Month DD YYYY)" for done
     * and not done task respectively.
     *
     * @return the template above for Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.getTime().format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
