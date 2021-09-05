package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class to create tasks that start at a specific time and ends at a specific time
 */
public class Event extends Task {
    protected String time;
    protected String timeToDisplay;
    protected LocalDateTime eventStart;
    protected LocalDateTime eventEnd;

    /**
     * To create event tasks
     * @param description the name/description of the task
     * @param time The times at which the event starts and ends
     */
    public Event(String description, String time) {
        super(description);
        assert !(description.equals("")) : "Event description is empty";

        assert (time.length() == 22) : "Timestamp is incorrect.";
        this.time = time;

        int date = Integer.parseInt(time.substring(0, 2));
        assert (date > 0 && date < 32) : "Date is not valid";

        int month = Integer.parseInt(time.substring(3, 5));
        assert (month > 0 && month < 13) : "Month is not valid";

        int year = Integer.parseInt(time.substring(6, 10));
        assert (year > 0) : "Year is not valid";

        int startHour = Integer.parseInt(time.substring(11, 13));
        assert (startHour >= 0 && startHour < 24) : "Start hour is not valid";

        int startMin = Integer.parseInt(time.substring(14, 16));
        assert (startMin >= 0 && startMin < 60) : "Start minute is not valid";

        int endHour = Integer.parseInt(time.substring(17, 19));
        assert (endHour >= 0 && endHour < 24) : "End hour is not valid";

        int endMin = Integer.parseInt(time.substring(20, 22));
        assert (endMin >= 0 && endMin < 60) : "End minute is not valid";

        LocalDateTime start = LocalDateTime.of(year, month, date, startHour, startMin);
        LocalDateTime end = LocalDateTime.of(year, month, date, endHour, endMin);
        this.timeToDisplay = start.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")) +
                " to " + end.format(DateTimeFormatter.ofPattern("HH:mm"));
        this.eventStart = start;
        this.eventEnd = end;
    }

    /**
     * Returns the time at which the event starts as a LocalDateTime object
     * @return the time at which the event starts as a LocalDateTime object
     */
    public LocalDateTime getEventStart() {
        return this.eventStart;
    }

    /**
     * Returns the time at which the event ends as a LocalDateTime object
     * @return the time at which the event ends as a LocalDateTime object
     */
    public LocalDateTime getEventEnd() {
        return this.eventEnd;
    }

    /**
     * Returns the task as a string that is to be displayed to the user
     * @return the given task as a string that is to be displayed to the user
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + timeToDisplay + ")";
    }

    /**
     * Returns the task as a string that is to be appended to the contents of the list of
     * tasks in user's hard disk
     * @return the task as a string that is to be appended to the contents of the list of
     * tasks in user's hard disk
     */
    @Override
    public String toPrintToFile() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
