package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that starts and ends at a specific time.
 * E.g. team project meeting at Oct-10-2019 1300hrs
 */
public class Event extends Task {
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Class constructor
     * @param description Description of the event.
     * @param at Time and Date of event.
     */
    public Event(String description, String at) {
        super(description);
        this.date = LocalDate.parse(at.split(" ", 2)[0]);
        this.time = LocalTime.parse(at.split(" ", 2)[1]);
    }

    /**
     * Returns the date of the event formatted as [MMM-dd-yyyy]
     *
     * @return The formatted date.
     */
    public String getDate() {
        DateTimeFormatter dateFormatObj = DateTimeFormatter.ofPattern("MMM-dd-yyyy");
        String formattedDate = date.format(dateFormatObj);
        return formattedDate;
    }

    /**
     * Returns the time of the event formatted as [HH:mm].
     *
     * @return The formatted time.
     */
    public String getTime() {
        DateTimeFormatter timeFormatObj = DateTimeFormatter.ofPattern("HH:mm");
        String formattedTime = time.format(timeFormatObj);
        return formattedTime;
    }

    /**
     * Returns formatted string to write to the file.
     *
     * @return String to write to duke.txt
     */
    @Override
    public String toWrite() {
        String done = this.isDone ? "1" : "0";
        return String.format("E | %s | %s | %s %s", done, this.getDescription(), this.date, this.time);
    }

    /**
     * Returns the string representation of the Event.
     *
     * @return String representation of Event.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s %s hrs)",super.toString(),this.getDate(), this.getTime());
    }
}
