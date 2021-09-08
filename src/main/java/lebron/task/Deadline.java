package lebron.task;

import lebron.exception.InvalidInputException;
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

    private final LocalDate date;
    private final LocalTime time;

    /**
     * Constructor.
     *
     * @param description the name of the task
     * @param date the date of the deadline in dd/MM/yyyy
     * @param time the time of the deadline in HHmm
     */
    public Deadline(String description, String date, String time) throws LebronException {
        super(description);
        try {
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
                + " " + time.format(DateTimeFormatter.ofPattern("HHmm"))+ ")";
    }

    @Override
    public String getStringForFile() {
        return "D | " + super.getDoneValue() + " | " + super.getName() + " | " + this.date + " " + this.time;
    }
}
