package jwbot.data.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for the deadline task
 *
 * @author Yim Jaewon
 */
public class Deadline extends Task {

    /**
     * the date or time of the event.
     */
    protected LocalDate by;

    /**
     * The constructor of the deadline task.
     *
     * @param description the description of the deadline task.
     * @param by          the date or time of the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    public Deadline(Deadline deadline) {
        super(deadline.description, deadline.isDone);
        this.by = deadline.by;
    }

    /**
     * The constructor of the deadline task.
     *
     * @return the protected field by.
     */
    public LocalDate getBy() {
        return by;
    }

    /**
     * override toString method for easier printing.
     *
     * @return the stingified task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
