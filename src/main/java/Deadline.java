import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A task that stores the deadline.
 */
public class Deadline extends Task {
    /**
     * The date of the deadline.
     */
    protected LocalDate date;

    /**
     * The time of the deadline.
     */
    protected LocalTime time;

    /**
     * A constructor used to initialize the deadline.
     *
     * @param description the description of the deadline.
     * @param date the date of the deadline
     * @param time the time of the deadline
     */
    protected Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    /**
     * Return the string representation of deadline.
     *
     * @return the string representation of deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("d/MM/yyyy")) + ")";
    }
}
