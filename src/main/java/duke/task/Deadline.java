package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String by;
    protected LocalDate date;

    /**
     * Creates a Deadline object which is not done by default.
     * @param content content in this task
     * @param by time of deadline
     */
    public Deadline(String content, String by) {
        super(content);
        this.by = by;
        try {
            date = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            date = null;
        }
    }

    /**
     * Creates a Deadline object.
     * @param content content in this task
     * @param by time of deadline
     * @param isDone specify whether task is done
     */
    public Deadline(String content, String by, boolean isDone) {
        super(content, isDone);
        this.by = by;
        try {
            date = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            date = null;
        }
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public String encoding() {
        return "D&&" + super.encoding() + "&&" + by;
    }

    @Override
    public String toString() {
        String time;
        if (date == null) {
            time = by;
        } else {
            time = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
        return "[D]" + super.toString() + " (by: " + time + ")";
    }
}
