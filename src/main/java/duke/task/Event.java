package duke.task;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event is a task that has a description, date, and a period of time.
 *
 * @author Loh Wen Hao Aaron
 *
 */
public class Event extends Task {
    private String timeStart;
    private String timeEnd;

    /**
     * This is the constructor for the event task which takes in the description,
     * date and time.
     *
     * @param description The String description for the event.
     * @param date The String date for the event.
     * @param time The String time for the event.
     */
    public Event(String description, String date, String time) {
        super(description, date, time);
        String[] segments = this.time.split("-");
        this.timeStart = segments[0];
        this.timeEnd = segments[1];
    }

    @Override
    public String getFormattedDate() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dateFormatterOutput = DateTimeFormatter.ofPattern("MMM dd yyyy");
        LocalDate date = LocalDate.parse(this.date, dateFormatter);
        return date.format(dateFormatterOutput).toString();
    }

    @Override
    public String getFormattedTime() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("k:mm");
        DateTimeFormatter timeFormatterOutput = DateTimeFormatter.ofPattern("h.mma");
        LocalTime timeStart = LocalTime.parse(this.timeStart, timeFormatter);
        LocalTime timeEnd = LocalTime.parse(this.timeEnd, timeFormatter);
        return timeStart.format(timeFormatterOutput).toString() + " to "
                + timeEnd.format(timeFormatterOutput).toString();

    }

    @Override
    public String getReadableString() {
        String binaryStatus = this.isDone ? "1" : "0";
        return "E | " + binaryStatus + " | " + this.description + " | " + this.date + " " + this.time + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getFormattedDate() + " " + getFormattedTime() + ")";
    }
}
