package tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns date and time by which this task must be completed.
     *
     * @return date and time by which this task must be completed.
     */
    public String getBy() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
    }

    /**
     * Returns string representation of Deadline object.
     *
     * @return string representation of Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
            + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")) + ")";
    }

}
