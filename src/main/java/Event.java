import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Event class represents tasks that start at a specific time and ends at
 * a specific time e.g., team project meeting on 2/10/2019 2-4pm.
 *
 * @author Chng Zi Hao
 */
public class Event extends Task{
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    /**
     * Constructor for Event.
     *
     * @param description The description of the event.
     * @param eventTimeframe The timeframe of the event.
     */
    public Event(String description, String eventTimeframe) throws DukeException {
        super(description);
        String[] splitDateTime = eventTimeframe.split(" ", 2);
        if (splitDateTime.length != 2) {
            throw new DukeException("Missing Date/Time @_@");
        }
        String[] splitStartEnd = splitDateTime[1].split("-", 2);
        if (splitStartEnd.length != 2) {
            throw new DukeException("Missing Start/End Time @_@");
        }
        this.date = parseDate(splitDateTime[0]);
        this.startTime = parseTime(splitStartEnd[0]);
        this.endTime = parseTime(splitStartEnd[1]);
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
            throw new DukeException("Invalid Time @_@\nEvent time format: HHmm-HHmm");
        }
    }

    private String formatDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String date = this.date.format(formatter);
        return date + " " + this.startTime.toString() + "-" + this.endTime.toString();
    }

    @Override
    public boolean checkDate(String date) throws DukeException {
        return this.date.equals(this.parseDate(date));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.formatDateTime() + ")";
    }

}
