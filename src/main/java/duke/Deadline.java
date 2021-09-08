package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class is a task. The input must be in such a format
 * "deadline <deadline name> /by <deadline date>".
 *
 */
public class Deadline extends Task {
    protected boolean isDone;
    private final String DEADLINE = "[D]";
    protected String dateAndTime;
    protected LocalDateTime localDateTime;
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

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
        formatLocalDateTime();

        assert localDateTime != null : "Date should not be null";
        return DEADLINE + this.getStatusIcon() + " " + this.getDescription() + " (by: "
                + localDateTime.format(dtf) + ")";
    }

    @Override
    public Task getToggledDone() {
        Deadline toggledDeadline = new Deadline(description, dateAndTime);
        if (this.getDone()) {
            toggledDeadline.setUndone();
        } else {
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
}
