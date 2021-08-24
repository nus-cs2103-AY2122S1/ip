package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event is a specific type of task that contains the description of the task.
 */
public class Event extends Task {
    private static final char TASK_LETTER = 'E';
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private LocalDate date;
    private String timeRange;

    /**
     * Constructs a event task.
     *
     * @param description The description of the task.
     * @param date The date of the event.
     * @param timeRange The start and end time of the event.
     */
    public Event(String description, LocalDate date, String timeRange) {
        super(description);
        this.date = date;
        this.timeRange = timeRange;
    }

    /**
     * Constructs a event task. It is used to instantiate an event that is already marked as done.
     * @param description The description of the task.
     * @param date The date of the event.
     * @param timeRange The start and end time of the event.
     * @param isDone Whether the event is done or not.
     */
    public Event(String description, LocalDate date, String timeRange, boolean isDone) {
        super(description, isDone);
        this.date = date;
        this.timeRange = timeRange;
    }

    /**
     * Returns string representation of an event.
     *
     * @return A string representing the event.
     */
    @Override
    public String toString() {
        return String.format("[%c]%s (at: %s %s)", Event.TASK_LETTER,
                super.toString(), this.date.format(Event.DATE_TIME_FORMATTER), this.timeRange);
    }

    /**
     * Convert the event to a string that can be saved to a file and converted back to itself.
     *
     * @return The string to be stored.
     */
    @Override
    public String stringToStore() {
        return Event.TASK_LETTER + " | " + this.getStatusIcon() + " | " + this.description + " | "
                + this.date.format(Event.DATE_TIME_FORMATTER) + " | " + this.timeRange + "\n";
    }
}
