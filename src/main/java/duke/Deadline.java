package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Creates Deadline tasks with descriptions and date and time.
 */
public class Deadline extends Task {

    private LocalDateTime dateTimeBy;
    private String by;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");

    /**
     * Creates a Deadline object from user command using the description and date and time.
     *
     * @param description describes the nature of the task.
     * @param by is the date and time by which the task is due, converted to LocalDateTime object.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.dateTimeBy = LocalDateTime.parse(by, formatter);
    }

    /**
     * Creates a Deadline object from a disk task string using the description,
     * number representing done status and date and time.
     *
     * @param num number retrieved from the drive that determines if the task is done or not.
     * @param description describes the nature of the task.
     * @param by is the date and time by which the task is due, converted to LocalDateTime object.
     */
    public Deadline(String num, String description, String by) {
        this(description, by);
        this.isDone = !num.equals(NOT_DONE_STRING);
        this.dateTimeBy = LocalDateTime.parse(by, formatter);
    }

    /**
     * Returns a formatted date and time from the LocalDateTime object.
     *
     * @return date and time following the format: MMM dd yyyy h:mm a.
     */
    public String getFormattedDateTime() {
        return this.dateTimeBy.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a"));
    }

    /**
     * Returns the string format in which to store the task to the disk, which is D | 1 or 0 | description | datetime.
     *
     * @return new string format in which to store the task to the disk which is different from toString.
     */
    @Override
    public String getFileString() {
        return String.format("D | %d | %s | %s", this.isDone ? DONE : NOT_DONE, this.description, this.by);
    }

    /**
     * Returns a string in the form of "[D] (done status) (description) (by: datetime)" when task is printed.
     *
     * @return string that is displayed when task is printed.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getFormattedDateTime() + ")";
    }
}
