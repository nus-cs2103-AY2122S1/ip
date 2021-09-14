package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.IncompleteTaskDescriptionException;

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
        String formattedDate = this.date.format(Task.DATE_TIME_FORMATTER);
        return String.format("[%c]%s (at: %s %s)", Event.TASK_LETTER,
                super.toString(), formattedDate, this.timeRange);
    }

    /**
     * Converts the event to a string that can be saved to a file and converted back to itself.
     *
     * @return The string to be stored.
     */
    @Override
    public String stringToStore() {
        String formattedDate = this.date.format(Task.DATE_TIME_FORMATTER);
        return String.format("%c | %s | %s | %s | %s\n",
                Event.TASK_LETTER, this.getStatusIcon(), this.description, formattedDate, this.timeRange);
    }

    /**
     * Creates a new event object.
     *
     * @param description The description of the task.
     * @param isDone Whether the event is done.
     * @return The event object created.
     * @throws IncompleteTaskDescriptionException If the description is empty or in incorrect format.
     */
    public static Event create(String description, boolean isDone) throws IncompleteTaskDescriptionException {
        if (description.matches("[^ ].* /at *[^ ].* [^ ].*")) {
            String eventSeparator = "/at";
            int separatorIndex = description.indexOf(eventSeparator);
            String taskDetail = description.substring(0, separatorIndex).trim();
            int len = eventSeparator.length();
            String at = description.substring(separatorIndex + len).trim();
            try {
                int index = at.indexOf(' ');
                LocalDate date = LocalDate.parse(at.substring(0, index));
                String timeRange = at.substring(index + 1).trim();
                return new Event(taskDetail, date, timeRange, isDone);
            } catch (DateTimeParseException e) {
                throw new IncompleteTaskDescriptionException("event");
            }
        } else {
            throw new IncompleteTaskDescriptionException("event");
        }
    }
}
