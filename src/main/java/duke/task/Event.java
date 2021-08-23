package main.java.duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event is a task that has a date in which it is happening.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S1
 */
public class Event extends Task {

    private final LocalDateTime date;

    /**
     * Constructor for Deadline task.
     *
     * @param isDone      true if the task is done
     * @param description the description
     * @param date        the date for which the event is occurring
     */
    public Event(boolean isDone, String description, String date) {
        super(description, isDone);
        this.date = LocalDateTime.parse(date.replace(" ", ""),
                DateTimeFormatter.ofPattern("yyyy-MM-ddHHmm"));
    }

    /**
     * Returns the string representation of the Deadline task.
     *
     * @return the string representation of the Deadline task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (at: " + DateTimeFormatter.ofPattern("d MMM uuuu h.mma").format(this.date) + ")";
    }

    /**
     * Returns if the task date is equal to the date provided.
     *
     * @param date the date provided
     * @return true if they are both equal
     */
    @Override
    public boolean onDate(LocalDate date) {
        return this.date.toLocalDate().equals(date);
    }
}
