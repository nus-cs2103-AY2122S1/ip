package duke.tasks;

import duke.DukeException;
import duke.commands.EditCommand;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class represents an Event Task.
 */
public class Event extends Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d-M-uuuu H:mm");
    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("d MMM uuuu hh:mm a");
    protected LocalDateTime date;

    /**
     * Constructs a Deadline Task that is not done.
     *
     * @param description Description of event task.
     * @param at Date that event is occurring.
     */
    public Event(String description, String at) throws DukeException {
        super(description, false);
        this.date = parseDateTime(at);
    }

    /**
     *
     * @param description Description of event task.
     * @param at Date that event is occurring.
     * @param isDone Whether task is done.
     */
    public Event(String description, String at, boolean isDone) throws DukeException {
        super(description, isDone);
        this.date = parseDateTime(at);
    }
    
    private LocalDateTime parseDateTime(String at) throws DukeException {
        try {
            return LocalDateTime.parse(at, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DukeException("Unable to parse datetime. Datetime should be input in this format: "
                    + "dd-mm-yyyy hh:mm");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toFileString() {
        return "E" + super.toFileString() + " | " + date.format(FORMATTER);
    }

    /**
     * {@inheritdoc}
     * Appends "[E]" to the front to show that Task is a Deadline.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DISPLAY_FORMATTER) + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event other = (Event) obj;
            return super.equals(obj) && date.equals(other.date);
        }
        return false;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public Task getUpdatedTask(EditCommand edit) throws DukeException {
        String newDescription = edit.getDescription() == null ? description : edit.getDescription();
        String newDate = edit.getDate() == null ? date.format(FORMATTER) : edit.getDate();
        return new Event(newDescription, newDate, isDone);
    }
}
