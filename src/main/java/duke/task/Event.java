package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Class to encapsulate a Event
 */
public class Event extends Task implements Achievable {

    protected String at;

    /**
     * Class constructor for Event Class specifying the description and the date 'at'
     */
    public Event(String description, String at) {
        super(description);
        changeDate(at);
    }

    /**
     * Class constructor for Event Class specifying the description, isDone and the date 'at'
     */
    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Changes the datetime of the Event.
     *
     * @param at the dateTime of the Event
     */
    @Override
    public void changeDate(String at) {
        String[] dateTime = at.split(" ");

        String rawDate = dateTime[0];
        String[] dayMonthYear = rawDate.split("/");
        LocalDate date = LocalDate.of(Integer.parseInt(dayMonthYear[2]), Integer.parseInt(dayMonthYear[1]),
                Integer.parseInt(dayMonthYear[0]));
        String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        assert dateTime.length > 1;
        // store date and time
        String rawTime = dateTime[1];
        String processedRawTime = rawTime.substring(0, 2) + ":" + rawTime.substring(2);
        LocalTime time = LocalTime.parse(processedRawTime);
        String formattedTime = time.format(DateTimeFormatter.ofPattern("h a"));

        this.at = formattedDate + ", " + formattedTime;
    }

    /**
     * Return the toString of the class
     *
     * @return           toString of the class
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Return the string format for data source
     *
     * @return           string format of task for data source
     */
    @Override
    public String toData() {
        return "E" + super.toData() + " | " + this.at;
    }
}
