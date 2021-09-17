package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Creates Event tasks with descriptions and date and time.
 */
public class Event extends Task {

    private LocalDateTime dateTimeAt;
    private String at;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");

    /**
     * Creates an Event object from user command using the description and date and time.
     *
     * @param description describes the nature of the task.
     * @param at is the date and time at which the task is occurring, converted to LocalDateTime object.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.dateTimeAt = LocalDateTime.parse(at, formatter);
    }

    /**
     * Creates an Event object from a disk task string using the description, number
     * representing done status and date and time.
     *
     * @param num number retrieved from the drive that determines if the task is done or not.
     * @param description describes the nature of the task.
     * @param at is the date and time at which the task is occurring, converted to LocalDateTime object.
     */
    public Event(String num, String description, String at) {
        this(description, at);
        this.isDone = !num.equals(NOT_DONE_STRING);
        this.dateTimeAt = LocalDateTime.parse(at, formatter);
    }

    /**
     * Returns a formatted date and time from the LocalDateTime object.
     *
     * @return date and time following the format: MMM dd yyyy h:mm a.
     */
    public String getFormattedDateTime() {
        return this.dateTimeAt.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a"));
    }

    /**
     * Returns the string format in which to store the task to the disk, which is E | 1 or 0 | description | datetime.
     *
     * @return new string format in which to store the task to the disk which is different from toString.
     */
    @Override
    public String getFileString() {
        return String.format("E | %d | %s | %s", this.isDone ? DONE : NOT_DONE, this.description, this.at);
    }

    /**
     * Returns a string in the form of "[E] (done status) (description) (at: datetime)" when task is printed.
     *
     * @return string that is displayed when task is printed.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getFormattedDateTime() + ")";
    }
}

