package banana;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;


/**
 * This class handles deadline
 * types of tasks.
 *
 * @author: Ravi Ananya
 */
class Deadline extends Task {

    protected String deadline;
    protected LocalDate date;
    protected String symbol;
    protected String dateFormat;

    /**
     * Constructor for Deadline.
     *
     * @param description user input.
     * @param deadline    date, day and/or time.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
        symbol = "[D]";
        dateFormat = "";
    }

    /**
     * Constructor for Deadline.
     *
     * @param description user input.
     * @param date        for official dates
     * @param deadline    date, day and/or time.
     */
    public Deadline(String description, LocalDate date, String deadline) {
        super(description);
        this.date = date;
        this.deadline = deadline;
        symbol = "[D]";
        dateFormat = date.format(
                DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Gets the deadline.
     *
     * @return the deadline
     */
    public String getDeadLine() {
        if (date != null) {
            return dateFormat + " " + deadline;
        } else {
            return deadline;
        }
    }

    @Override
    public String toString() {
        if (date == null) {
            return symbol + super.toString() + " (by: "
                    + deadline + ")";
        } else {
            return symbol + super.toString() + " (by: "
                    + dateFormat + " " + deadline + ")";
        }
    }

}
