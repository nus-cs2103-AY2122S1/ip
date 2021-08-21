import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event Class represents a task that starts and ends at specific timings.
 *
 * It contains information relating to the task:
 * - description
 * - isDone
 * - eventDate
 * - startTime
 * - endTime
 *
 * @author Benedict Chua
 */
public class Event extends Task {
    private LocalDate eventDate;
    private LocalTime startTime;
    private LocalTime endTime;

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

    public Event(String description, String eventTime) {
        super(description);
        String[] dateInfo = eventTime.split(" ", 3);
        if (dateInfo.length < 3) {
            throw new MissingArgumentException("Date or Time", "Event");
        }
        this.eventDate = parseDate(dateInfo[0]);
        this.startTime = parseTime(dateInfo[1]);
        this.endTime = parseTime(dateInfo[2]);
    }

    public Event(String completed, String description, String eventTime) {
        super(description);
        String[] dateInfo = eventTime.split(" ", 3);
        if (dateInfo.length < 3) {
            throw new MissingArgumentException("Date or Time", "Event");
        }
        this.eventDate = parseDate(dateInfo[0]);
        this.startTime = parseTime(dateInfo[1]);
        this.endTime = parseTime(dateInfo[2]);

        if (completed.equals("1")) {
            super.markTaskAsDone();
        }
    }

    public String formatDate() {
        return eventDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public boolean onDate(String date) {
        return this.eventDate.equals(parseDate(date));
    }

    @Override
    public String saveAsString() {
        return super.formatString("E", String.format("%s %s %s", this.eventDate, this.startTime, this.endTime));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (by: %s %s-%s)", super.toString(), formatDate(), startTime, endTime);
    }
}