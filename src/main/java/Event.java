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
        this.date = Parser.parseDate(splitDateTime[0]);
        this.startTime = Parser.parseTime(splitStartEnd[0]);
        this.endTime = Parser.parseTime(splitStartEnd[1]);
    }

    private String formatDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String date = this.date.format(formatter);
        return date + " " + this.startTime.toString() + "-" + this.endTime.toString();
    }

    @Override
    public boolean checkDate(LocalDate date) throws DukeException {
        return this.date.equals(date);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.formatDateTime() + ")";
    }

    @Override
    public String format() {
        return String.format("E | %s | %s %s-%s", super.format(), this.date, this.startTime, this.endTime);
    }
}
