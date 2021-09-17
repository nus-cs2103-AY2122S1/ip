package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a subclass of task
 * with a due date.
 *
 * @author Samuel Lau
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDate date;
    protected String afterDate;

    /**
     * Constructor for Deadline class.
     *
     * @param description The description of the task.
     * @param by The deadline date of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        String[] arr = by.split(" ", 2);
        this.afterDate = arr.length == 2 ? arr[1] : "";
        this.date = LocalDate.parse(arr[0]);
    }

    /**
     * Returns the string in the written format
     * to be saved in the text file.
     *
     * @return String to be saved.
     */
    @Override
    public String toWrite() {
        int marked = this.isDone ? 1 : 0;
        return String.format("D|%d|%s|%s\n", marked, this.description, this.by);
    }

    /**
     * Returns the string representation of the deadline object.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s %s)", super.toString(),
                this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")), this.afterDate);
    }
}