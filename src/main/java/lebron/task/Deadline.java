package lebron.task;

import lebron.exception.LebronException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class represents the Deadline event.
 *
 * @author Nigel Tan
 */
public class Deadline extends Task {
    private final String strTime;
    private final String strDate;
    private final LocalDate date;
    private final LocalTime time;

    /**
     * Constructor.
     *
     * @param description the name of the task.
     * @param date the date of the deadline in yyyy-mm-dd.
     * @param time the time of the deadline in HHmm.
     */
    public Deadline(String description, String date, String time) throws LebronException {
        super(description);
        try {
            this.strDate = date;
            this.strTime = time;
            this.date = LocalDate.parse(date);
            this.time = LocalTime.parse(time, DateTimeFormatter.ofPattern("HHmm"));;
        } catch (DateTimeParseException e) {
            throw new LebronException("    :( OOPS! Please check that your date and time is " +
                    "valid and formatted as 'yyyy-MM-dd' 'HHmm'.");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                + " " + this.time + ")";
    }

    /**
     * Produces the desired format for storing to file.
     *
     * @return the desired string.
     */
    @Override
    public String getStringForFile() {
        return "D | " + super.getDoneValue() + " | " + super.getName() + " | " + this.date + " " +
                time.format(DateTimeFormatter.ofPattern("HHmm"));
    }

    /**
     * Overrides the makeCopy() method in Task.
     *
     * @return the copied task.
     * @throws LebronException if the date/time is in wrong format.
     */
    @Override
    public Deadline makeCopy() throws LebronException {
        return new Deadline(this.getName(), this.strDate, this.strTime);
    }
}
