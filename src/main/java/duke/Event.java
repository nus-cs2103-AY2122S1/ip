package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class to create tasks that start at a specific time and ends at a specific time
 */
public class Event extends Task {
    //Includes both start time and end time, in the format of hh:mm-hh:mm
    protected String time;

    //The time format to be displayed to users, in the format of dd MMM yyyy hh:mm to hh:mm
    protected String timeToDisplay;

    //The start time of the event
    protected LocalDateTime eventStart;

    //The end time of the event
    protected LocalDateTime eventEnd;

    /**
     * To create event tasks
     *
     * @param description the name/description of the task
     * @param time The times at which the event starts and ends
     */
    public Event(String description, String time) {
        super(description);

        this.time = time;

        //Extracting the date, month and year numbers from the 'time' string
        int date = Integer.parseInt(time.substring(0, 2));
        int month = Integer.parseInt(time.substring(3, 5));
        int year = Integer.parseInt(time.substring(6, 10));

        //Extracting the event start time hour and minute numbers from the 'time' string
        int startHour = Integer.parseInt(time.substring(11, 13));
        int startMin = Integer.parseInt(time.substring(14, 16));

        //Extracting the event end time hour and minute numbers from the 'time' string
        int endHour = Integer.parseInt(time.substring(17, 19));
        int endMin = Integer.parseInt(time.substring(20, 22));

        //Creating LocalDateTime objects for the start and end times of the event so that their formats can be changed
        //later
        LocalDateTime start = LocalDateTime.of(year, month, date, startHour, startMin);
        eventStart = start;

        LocalDateTime end = LocalDateTime.of(year, month, date, endHour, endMin);
        eventEnd = end;

        //Formatting the time to be displayed to users
        this.timeToDisplay = start.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")) +
                " to " + end.format(DateTimeFormatter.ofPattern("HH:mm"));


    }

    /**
     * Returns the time at which the event starts as a LocalDateTime object
     *
     * @return the time at which the event starts as a LocalDateTime object
     */
    public LocalDateTime getEventStart() {
        return this.eventStart;
    }

    /**
     * Returns the time at which the event ends as a LocalDateTime object
     *
     * @return the time at which the event ends as a LocalDateTime object
     */
    public LocalDateTime getEventEnd() {
        return this.eventEnd;
    }

    /**
     * Returns the task as a string that is to be displayed to the user
     *
     * @return the given task as a string that is to be displayed to the user
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + timeToDisplay + ")";
    }

    /**
     * Returns the task as a string that is to be appended to the contents of the list of
     * tasks in user's hard disk
     *
     * @return the task as a string that is to be appended to the contents of the list of
     * tasks in user's hard disk
     */
    @Override
    public String toPrintToFile() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
