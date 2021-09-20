package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.commands.EditCommand;

/**
 * This class represents a Deadline Task.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d-M-uuuu H:mm");
    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("d MMM uuuu hh:mm a");
    protected LocalDateTime date;

    /**
     * Constructs a Deadline Task that is not done.
     *
     * @param description Description of deadline task.
     * @param by Date that task has to be completed by.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description, false);
        this.date = parseDateTime(by);
    }

    /**
     * Constructs a Deadline Task which can be marked as done.
     *
     * @param description Description of deadline task.
     * @param by Date that task has to be completed by.
     * @param isDone Whether the task is done.
     */
    public Deadline(String description, String by, boolean isDone) throws DukeException {
        super(description, isDone);
        this.date = parseDateTime(by);
    }

    private LocalDateTime parseDateTime(String by) throws DukeException {
        try {
            return LocalDateTime.parse(by, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DukeException("Unable to parse datetime. Datetime should be input in this format: "
                    + "dd-mm-yyyy hh:mm");
        }
    }

    /**
     * {@inheritDoc}
     * Appends "[D]" to the front to show that Task is a Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DISPLAY_FORMATTER) + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toFileString() {
        return "D" + super.toFileString() + " | " + date.format(FORMATTER);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline) {
            Deadline other = (Deadline) obj;
            return super.equals(obj) && date.equals(other.date);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task getUpdatedTask(EditCommand edit) throws DukeException {
        String newDescription = edit.getDescription() == null ? description : edit.getDescription();
        String newDate = edit.getDate() == null ? date.format(FORMATTER) : edit.getDate();
        return new Deadline(newDescription, newDate, isDone);
    }
}
