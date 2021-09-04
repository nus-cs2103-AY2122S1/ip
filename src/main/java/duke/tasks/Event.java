package duke.tasks;

import duke.exceptions.IllegalFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates the information for an Event object that contains the description, completion status and due date.
 */
public class Event extends Task {
    public static final String TAG = "E";
    private LocalDate dateOfEvent;

    /**
     * Constructs a new Event object with the specified task description, due date and task status.
     *
     * @param description Description/Tile of the task.
     * @param dateOfEvent Due date of the task.
     * @param isDone Completion status of the task.
     */
    public Event(String description, LocalDate dateOfEvent, boolean isDone) {
        super(description, isDone);
        this.dateOfEvent = dateOfEvent;
    }

    @Override
    public String getTag() {
        return Event.TAG;
    }

    @Override
    public String getDueDate() {
        return this.dateOfEvent.toString().trim();
    }

    @Override
    public void setDate(String date) throws DateTimeParseException {
        this.dateOfEvent = LocalDate.parse(date);
    }

    @Override
    public String toString() {
        return "[" + Event.TAG + "]" + super.toString()
                + " (at: " + this.dateOfEvent.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
