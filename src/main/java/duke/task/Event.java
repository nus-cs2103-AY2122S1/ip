package duke.task;

import java.time.LocalDate;

/**
 * This class is a subclass of task.
 *
 * @author Deng Huaiyu(G12)
 * @version CS2103T AY21/22 Semester 1
 */
public class Event extends Task {
    protected LocalDate date;
    private String time;

    /**
     * The construction method for an event.
     *
     * @param description detail of an event
     * @param date time of a deadline
     */
    public Event(String description, LocalDate date, String time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    /**
     * Returns date
     * @return date in the form of LocalDate
     */
    @Override
    public LocalDate getDate() {
        return date;
    }

    /**
     * Outputs after transforming to String an event.
     *
     * @return return the string form of the event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + Task.toDateFormat(date) + " " + time + ")";
    }

    /**
     * Gets type of the event
     *
     * @return String of the type
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * Gets time of the event
     *
     * @return time of the event
     */
    @Override
    public String getTime() {
        return date.toString() + " | " + time;
    }
}
