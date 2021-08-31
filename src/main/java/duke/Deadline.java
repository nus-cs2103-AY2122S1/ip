package duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

/**
 * Represents a task that has a deadline.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;
    private static final DateTimeFormatter SAVE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a");

    /**
     * Creates a Deadline object.
     *
     * @param desc The description of the task.
     * @param deadline The deadline of the task.
     * @throws DukeException Throws if fails to create Deadline object.
     */
    public Deadline(String desc, String deadline) throws DukeException {
        super(desc.trim());
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(deadline);
            LocalDateTime newDT = new java.sql.Timestamp(date.getTime()).toLocalDateTime();
            this.deadline = newDT;
        } catch (DateTimeParseException | ParseException e) {
            throw new DukeException();
        }
    }

    /**
     * Creates a Deadline object.
     *
     * @param desc The description of the task.
     * @param deadline The deadline of the task.
     * @param isDone The initial status of the task.
     * @throws DukeException Throws if fails to create Deadline object.
     */
    public Deadline(String desc, String deadline, boolean isDone) throws DukeException {
        super(desc.trim(), isDone);
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(deadline);
            LocalDateTime newDT = new java.sql.Timestamp(date.getTime()).toLocalDateTime();
            this.deadline = newDT;
        } catch (ParseException e) {
            throw new DukeException();
        }
    }

    /**
     * Returns the task as a String formatted to be saved.
     *
     * @return The formatted String.
     */
    @Override
    public String saveTask() {
        return "D|" + this.isDone() + "|" + getDesc() + "|" + deadline.format(SAVE_FORMAT) + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + this.statusIcon() + this.getDesc() + " (by: " + deadline.format(FORMATTER) + ")";
    }
}
