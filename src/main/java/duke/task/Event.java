package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates an event task.
 * Event tasks are tasks that needs to be done on a specific date
 * from a starting time to an ending time.
 */
public class Event extends Task {
    private final LocalDate date;
    private final LocalTime startTime;
    private final LocalTime endTime;

    /**
     * Constructor for Event.
     *
     * @param description description of event task
     * @param date date of event task
     * @param startTime start time of event task
     * @param endTime end time of event task
     */
    public Event(String description, LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(description);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        assert !description.equals("") : "Event task description cannot be empty";
    }

    /**
     * Gets string representation of event task.
     *
     * @return String representation of event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mma");
        return "[E]" + super.toString() + " (at: "
                + date.format(dateFormatter) + " "
                + startTime.format(timeFormatter) + "-"
                + endTime.format(timeFormatter) + ")";
    }
}
