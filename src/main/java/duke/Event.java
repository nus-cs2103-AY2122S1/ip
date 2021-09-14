package duke;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event class is a task. The input must be in such a format
 * "Event <event name> /at <event date>".
 */
public class Event extends Task implements GeneralCommand {
    private final String EVENT = "[E]";
    protected String dateAndTime;
    protected LocalDateTime localDateTime;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs an event.
     *
     * @param description Description of the event.
     * @param dateAndTime Date and time of the event.
     */
    public Event(String description, String dateAndTime) {
        super(description);
        this.dateAndTime = dateAndTime;
    }

    /**
     * Constructs an event.
     *
     * @param command Parsed command.
     * @param storage Storage to be updated.
     * @param tasks TaskList of current tasks.
     * @param ui Ui to return String.
     * @throws DukeException If event command is empty.
     */
    public Event(String command, Storage storage, TaskList tasks, Ui ui) throws DukeException {
        super(command);
        this.storage = storage;
        this.tasks = tasks;
        this.ui = ui;
        String desc = command.substring(5);

        if (desc.isEmpty()) {
            throw new DukeException("event", "'event project meeting /at 2021-08-27 19:15'");
        }
        assert desc.substring(1).length() > 0 : "Description should be present";
        int escapeIndex = command.lastIndexOf("/");
        this.description = command.substring(6, escapeIndex - 1);
        this.dateAndTime = command.substring(escapeIndex + 4);
    }

    /**
     * Returns the date and time of the event in a string.
     *
     * @return Returns the date and time of the event.
     */
    public String getDate() {
        return this.dateAndTime;
    }

    /**
     * Formats the date and time in order to be
     * parsed into the DateTimeFormatter.
     */
    public void formatLocalDateTime() {
        if (this.dateAndTime.substring(0, 1).matches("[0-9]")) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            this.localDateTime = LocalDateTime.parse(dateAndTime, dateTimeFormatter);
        } else {
            this.localDateTime = LocalDateTime.parse(dateAndTime, dtf);
        }
    }

    /**
     * Returns the string of the event.
     *
     * @return String of event.
     */
    @Override
    public String toString() {
        formatLocalDateTime();
        assert localDateTime != null : "Date should not be null";
        return EVENT + this.getStatusIcon() + " " + this.getDescription() + " (at: " + localDateTime.format(dtf) + ")";
    }

    /**
     * Returns new Task with the same description as this, but an opposite status.
     *
     * @return Copy of this task object.
     */
    @Override
    public Task getToggledDone() {
        Event toggledEvent = new Event(description, dateAndTime);
        if (!this.getDone()) {
            toggledEvent.setDone();
        }
        return toggledEvent;
    }

    /**
     * Checks to see if two events are equal in description and status.
     * Returns false if object is not equal to this event.
     *
     * @param object Object compared to.
     * @return Boolean true if equal, false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof Event) {
            Event event = (Event) object;
            return (event.getDone() == this.getDone()) && event.description.equals(this.description);
        }
        return false;
    }

    /**
     * Executes Event and returns a String to be printed.
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
