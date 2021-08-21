import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class represents tasks that need to be done before a
 * specific date/time e.g., submit report by 11/10/2019 5pm.
 *
 * @author Chng Zi Hao
 */
public class Deadline extends Task{
    private LocalDate dueDate;
    private LocalTime dueTime;

    /**
     * Constructor for Deadline.
     *
     * @param description The description of the deadline.
     * @param by The due date of deadline.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        String[] splitDateTime = by.split(" ", 2);
        if (splitDateTime.length != 2) {
            throw new DukeException("Missing Date/Time @_@");
        }
        this.dueDate = parseDate(splitDateTime[0]);
        this.dueTime = parseTime(splitDateTime[1]);
    }

    private LocalDate parseDate(String date) throws DukeException {
        try {
            // Reuse regex from https://www.javacodeexamples.com/java-regular-expression-validate-date-example-regex/1504
            String[] dateSplit;
            if (date.matches("\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|[3][01])")) {
                // for yyyy-mm-dd
                return LocalDate.parse(date);
            } else if (date.matches("(0[1-9]|[12][0-9]|[3][01])-(0[1-9]|1[012])-\\d{4}")) {
                // for dd-mm-yyyy
                dateSplit = date.split("-", 3);
                return LocalDate.parse(String.format("%s-%s-%s", dateSplit[2], dateSplit[1], dateSplit[0]));
            } else if (date.matches("(0[1-9]|[12][0-9]|[3][01])/(0[1-9]|1[012])/\\d{4}")) {
                // for dd/mm/yyyy
                dateSplit = date.split("/", 3);
                return LocalDate.parse(String.format("%s-%s-%s", dateSplit[2], dateSplit[1], dateSplit[0]));
            } else {
                throw new DateTimeException("Invalid Date");
            }
        } catch (DateTimeException e) {
            throw new DukeException("Invalid Date @_@\nDate formats: dd/mm/yyyy, dd-mm-yyyy, yyyy-mm-dd");
        }
    }

    private LocalTime parseTime(String time) throws DukeException {
        try {
            // Reuse regex from https://www.geeksforgeeks.org/how-to-validate-time-in-24-hour-format-using-regular-expression/
            if (time.length() == 4) {
                String t = String.format("%s:%s", time.substring(0,2), time.substring(2,4));
                if (t.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")) {
                    return LocalTime.parse(t);
                } else {
                    throw new DateTimeException("Invalid Time");
                }
            } else {
                throw new DateTimeException("Invalid Time");
            }
        } catch (DateTimeException e) {
            throw new DukeException("Invalid Time @_@\nTime format: HHmm");
        }
    }

    private String formatDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String date = this.dueDate.format(formatter);
        return date + " " + this.dueTime.toString();
    }

    @Override
    public boolean checkDate(String date) throws DukeException {
        return this.dueDate.equals(this.parseDate(date));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.formatDateTime() + ")";
    }
}
