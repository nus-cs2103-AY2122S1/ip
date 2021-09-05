package banana;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;


/**
 * This class handles deadline
 * types of tasks.
 *
 * @author: Ravi Ananya
 **/

class Deadline extends Task {

    protected String deadline;
    protected LocalDate date;

    /**
     * Constructor for Deadline.
     *
     * @param description user input.
     * @param deadline    date, day and/or time.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
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
    }

    /**
     * Gets the deadline.
     *
     * @return the deadline
     */
    public String getDeadLine() {
        if (date != null) {
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + deadline;
        } else {
            return deadline;
        }
    }

    @Override
    public String toString() {
        if (date == null) {
            return "[D]" + super.toString() + " (by: " + deadline + ")";
        } else {
            return "[D]" + super.toString() + " (by: " +
                    date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + deadline + ")";
        }

    }

}
