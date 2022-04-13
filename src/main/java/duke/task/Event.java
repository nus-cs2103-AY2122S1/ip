package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Dr-Octavius
 *
 * Class that represents a Event Task.
 * Parent Class: Task
 *
 * @version 1.0
 */
public class Event extends Task {

    private final LocalDate date;
    private final LocalTime time;

    /**
     * Class Constructor that takes 3 parameters
     *
     * @param description Deadline Task description
     * @param state completion status of event
     * @param date Date when the Event will take place
     * @param time Time when the Event will take place
     */
    public Event(boolean state, String description, String date, String time) {
        super(TASKTYPE.E, description, state);
        this.date = LocalDate.parse(date);
        this.time = LocalTime.parse(time);
    }

    /**
     * Class Constructor that takes 2 parameters
     *
     * @param description Deadline Task description
     * @param date Date when the Event will take place
     * @param time Time when the Event will take place
     */
    public Event(String description, String date, String time) {
        super(TASKTYPE.E, description);
        this.date = LocalDate.parse(date);
        this.time = LocalTime.parse(time);
    }

    /**
     * Returns date the Task will take place
     *
     * @return String description of the date Task will be done
     */
    public LocalDate getDate() {
        assert date != null : "Event was created without a date";
        return date;
    }

    /**
     * Returns time the Task will take place
     *
     * @return String description of the time Task will be done
     */
    public LocalTime getTime() {
        assert time != null : "Event was created without a time";
        return time;
    }

    /**
     * String representation of a Deadline
     *
     * @return Summary of Event information
     */
    @Override
    public String toString() {
        DateTimeFormatter datef = DateTimeFormatter.ofPattern("MMM d yyyy");
        DateTimeFormatter timef = DateTimeFormatter.ofPattern("HHmm");
        String dataD = date.format(datef);
        String dataT = time.format(timef);
        return super.toString().concat(
                " (at: ".concat(dataD)
                        .concat(" ")
                        .concat(dataT)
                        .concat(")")
        );
    }
}
