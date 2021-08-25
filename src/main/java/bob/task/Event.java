package bob.task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

/**
 * Represents a special type of task with a specific date, meaning the task will occur or be completed at that date.
 */
public class Event extends Task {
    /** Date of event */
    private LocalDate date;

    /**
     * Constructor for a new Event task.
     * Calls the constructor of Task as well as the Event task is a task.
     *
     * @param description Description of the Event task.
     * @param date Date of the event.
     */
    public Event(String description, String date) {
        super(description);
        this.date = LocalDate.parse(date);
     }

    /**
     * Overrides the printTask() method in Task to print specifically the String representation of the Event task.
     * Calls the printTask() method in Task as well to form the general part of the String representation.
     *
     * @return String representation of the Event task.
     */
    @Override
    public String printTask() {
        return "[E] " + super.printTask() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
