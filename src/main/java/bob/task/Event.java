package bob.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import bob.exception.InvalidDateException;

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
     * @throws InvalidDateException If the user inputs an invalid date for the timing.
     */
    public Event(String description, String date) throws InvalidDateException {
        super(description);
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }

    /**
     * Overrides the printTask() method in Task to print specifically the String representation of the Event task.
     * Calls the printTask() method in Task as well to form the general part of the String representation.
     *
     * @return String representation of the Event task.
     */
    @Override
    public String printTask() {
        String eventHeader = "[E] ";
        String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return eventHeader + super.printTask() + " (at: " + formattedDate + ")";
    }
}
