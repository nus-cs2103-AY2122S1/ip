package duke;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

/**
 * Deadline class is a task. The input must be in such a format
 * "deadline <deadline name> /by <deadline date>".
 *
 */
public class Deadline extends Task implements GeneralCommand {
    protected boolean isDone;
    private final String DEADLINE = "[D]";
    protected String dateAndTime;
    protected LocalDateTime localDateTime;
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a deadline.
     *
     * @param description Description of the event.
     * @param dateAndTime Date and time of the event.
     */
    public Deadline(String description, String dateAndTime) {
        super(description);
        this.isDone = false;
        this.dateAndTime = dateAndTime;
        formatLocalDateTimeDefault();
    }

    /**
     * Constructs a deadline.
     *
     * @param command Parsed command.
     * @param storage Storage to be updated.
     * @param tasks TaskList of current tasks.
     * @param ui Ui to return String.
     * @throws DukeException If deadline command is empty.
     */
    public Deadline(String command, Storage storage, TaskList tasks, Ui ui) throws DukeException {
        super(command);
        String desc = command.substring(8);

        if (desc.isEmpty()) {
            throw new DukeException("deadline", "'deadline return book /by 2021-08-27 14:15'");
        }
        assert desc.substring(1).length() > 0 : "Description should be present";

        int escapeIndex = command.lastIndexOf("/");
        this.description = command.substring(9, escapeIndex - 1);
        this.storage = storage;
        this.tasks = tasks;
        this.ui = ui;
        this.dateAndTime = command.substring(escapeIndex + 4);
        formatLocalDateTime();
    }

    /**
     * Formats the date and time in order to be
     * parsed into the DateTimeFormatter.
     *
     * @throws DukeException If date is formatted wrongly
     */
    public void formatLocalDateTime() throws DukeException {
        try {
            if (this.dateAndTime.substring(0, 1).matches("[0-9]")) {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                this.localDateTime = LocalDateTime.parse(dateAndTime, dateTimeFormatter);
            } else {
                this.localDateTime = LocalDateTime.parse(dateAndTime, dtf);
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Please input the correct date in this format '2021-08-27 19:15'");
        }
    }

    /**
     * Formats the date and time in order to be
     * parsed into the DateTimeFormatter by constructor.
     */
    public void formatLocalDateTimeDefault() {
        if (this.dateAndTime.substring(0, 1).matches("[0-9]")) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            this.localDateTime = LocalDateTime.parse(dateAndTime, dateTimeFormatter);
        } else {
            this.localDateTime = LocalDateTime.parse(dateAndTime, dtf);
        }
    }

    /**
     * Returns the date and time of the deadline in a string.
     *
     * @return Returns the date and time of the deadline.
     */
    public String getDate() {
        return this.dateAndTime;
    }

    /**
     * Returns the string of the deadline.
     *
     * @return String of event.
     */
    @Override
    public String toString() {
        assert localDateTime != null : "Date should not be null";
        return DEADLINE + this.getStatusIcon() + " " + this.getDescription() + " (by: "
                + localDateTime.format(dtf) + ")";
    }

    /**
     * Returns new Task with the same description as this, but an opposite status.
     *
     * @return Copy of this task object.
     */
    @Override
    public Task getToggledDone() {
        Deadline toggledDeadline = new Deadline(description, dateAndTime);
        if (!this.getDone()) {
            toggledDeadline.setDone();
        }
        return toggledDeadline;
    }

    /**
     * Checks to see if two deadlines are equal in description and status.
     * Returns false if object is not equal to this deadline.
     *
     * @param object Object compared to.
     * @return Boolean true if equal, false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof Deadline) {
            Deadline deadline = (Deadline) object;
            return deadline.isDone == this.isDone && deadline.description.equals(this.description);
        }
        return false;
    }

    /**
     * Executes Deadline and returns a String to be printed.
     *
     * @return String to be printed on Gui.
     * @throws IOException If an input or output operation is failed or interpreted.
     */
    @Override
    public String execute() throws IOException {
        tasks.add(this);
        storage.save(tasks);
        return ui.taskMessageToString(this, tasks);
    }
}
