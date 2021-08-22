package main.java;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event is a task that has a date in which it is happening.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S2
 */
public class Event extends Task {

    private final LocalDateTime date;

    /**
     * Constructor for Deadline task.
     *
     * @param description the description
     */
    public Event(boolean isDone, String description, String date) {
        super(description, isDone);
        LocalDateTime dateTime = LocalDateTime.parse(date.replace(" ", ""), DateTimeFormatter.ofPattern("yyyy-MM-ddHHmm"));
        this.date = dateTime;
    }

    /**
     * Returns the string representation of the Deadline task.
     *
     * @return the string representation of the Deadline task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + DateTimeFormatter.ofPattern("d MMM uuuu hh:mma").format(this.date) + ")";
    }

    /**
     * Returns if the task date is equal to the date provided.
     *
     * @param date the date provided
     * @return true if they are both equal
     */
    @Override
    protected boolean onDate(LocalDate date) {
        return this.date.toLocalDate().equals(date);
    }
}
