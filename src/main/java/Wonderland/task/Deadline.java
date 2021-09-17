package Wonderland.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Wonderland.Wonderland.task.Deadline class extends Wonderland.Wonderland.task.Task class and encapsulate a deadline Wonderland.Wonderland.task
 * that need to be done before a specific date/time.
 */
public class Deadline extends Task {
    private final String symbol = "[D]";
    protected LocalDate by;

    /**
     * Constructor for a deadline object.
     *
     * @param description String description of deadline.
     * @param by LocalDate of deadline due date.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns string for saving deadline Wonderland.Wonderland.task to data file.
     *
     * @return string for saving deadline Wonderland.Wonderland.task to data file.
     */
    @Override
    public String toFileEntry() {
        return "D" +"/next"+ super.isDone + "/next" + super.description + "/next" + this.by.toString();
    }

    /**
     * Returns string representation of deadline object.
     *
     * @return string representation of deadline object.
     */
    @Override
    public String toString() {
        return this.symbol + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +")";
    }
}
