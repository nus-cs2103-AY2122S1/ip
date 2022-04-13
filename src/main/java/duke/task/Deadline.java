package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is an extension of task, which includes an endTime specified by the user.
 */
public class Deadline extends Task {

    private String endTime;

    /**
     * Initialises a deadline
     *
     * @param str The description of the deadline.
     * @param endTime The ending time of the deadline.
     */
    public Deadline(String str, LocalDateTime endTime) {
        super(str, endTime);
        DateTimeFormatter dateOnly = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
        this.endTime = dateOnly.format(endTime);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + endTime + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Deadline)) {
            return false;
        }
        Deadline other = (Deadline) obj;
        return this.endTime.equals(other.endTime);
    }
}
