import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline Class represents a task that needs to be done before a specific date/time.
 *
 * It contains information relating to the task:
 * - description
 * - isDone
 * - dueDate
 * - dueTime
 *
 * @author Benedict Chua
 */
public class Deadline extends Task {
    private LocalDate dueDate;
    private LocalTime dueTime;

    private LocalDate parseDate(String dateString) throws InvalidDateInputException {
        try {
            if (dateString.matches("\\d{2}-\\d{2}-\\d{4}")) {
                // in the form dd-mm-yyyy
                String[] d = dateString.split("-", 3);
                return LocalDate.parse(String.format("%s-%s-%s", d[2], d[1], d[0]));
            } else if (dateString.matches("\\d{2}/\\d{2}/\\d{4}")) {
                // in the form dd/mm/yyyy
                String[] d = dateString.split("/", 3);
                return LocalDate.parse(String.format("%s-%s-%s", d[2], d[1], d[0]));
            } else if (dateString.matches("\\d{4}-\\d{2}-\\d{2}")) {
                // in the form yyyy-mm-dd
                return LocalDate.parse(dateString);
            } else {
                throw new InvalidDateInputException(dateString);
            }
        } catch (DateTimeException e) {
            throw new InvalidDateInputException(dateString);
        }
    }

    private LocalTime parseTime(String timeString) throws InvalidTimeInputException {
        try {
            if (timeString.indexOf(':')  == -1 && timeString.length() == 4) {
                // in the form hhmm
                String[] t = {timeString.substring(0, 2), timeString.substring(2)};
                return LocalTime.parse(String.format("%s:%s", t[0], t[1]));
            } else if (timeString.indexOf(':')  == 2 && timeString.length() == 5) {
                //in the form hh:mm
                return LocalTime.parse(timeString);
            } else {
                // Maybe throw exception
                throw new InvalidTimeInputException(timeString);
            }
        } catch (DateTimeException e) {
            throw new InvalidTimeInputException(timeString);
        }
    }

    public Deadline(String description, String dueDate) {
        super(description);

        String[] dateInfo = dueDate.split(" ", 2);
        if (dateInfo.length < 2) {
            throw new MissingArgumentException("Date or Time", "Deadline");
        }
        this.dueDate = parseDate(dateInfo[0]);
        this.dueTime = parseTime(dateInfo[1]);
    }

    public String formatDate() {
        return dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public boolean onDate(String date) {
        return this.dueDate.equals(parseDate(date));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s %s)", super.toString(), formatDate(), dueTime);
    }
}