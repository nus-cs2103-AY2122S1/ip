package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String by;

    protected LocalDate date;
    private boolean hasDate;

    /**
     * Constructs a deadline instance with description and due time.
     *
     * @param description description of deadline
     * @param by due time. If given string is in format yyyy-mm-dd, a LocalDate instance will be created to
     *          store the date
     */
    protected Deadline(String description, String by) {
        super(description);
        assert by != null : "due time is null";

        try {
            date = LocalDate.parse(by);
            hasDate = true;
            this.by = by;
        } catch (DateTimeParseException e) {
            hasDate = false;
            this.by = by;
        }
    }

    /**
     * Constructs a deadline instance with description, due time and task status.
     *
     * @param description description of deadline
     * @param by due time. If given string is in format yyyy-mm-dd, a LocalDate instance will be created to
     *          store the date
     * @param isDone task status
     */
    protected Deadline(String description, String by, boolean isDone) {
        this(description, by);
        this.isDone = isDone;
    }

    @Override
    public String populateSaveData() {
        return "D | " + (this.isDone ? 1 : 0) + " | " + this.description + " | " + this.by;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o != null && o.getClass() == this.getClass()) {
            Deadline d = (Deadline) o;
            if (d.isDone == this.isDone && d.description.equals(this.description)) {
                if (this.hasDate) {
                    return d.hasDate && this.date.equals(d.date);
                } else {
                    return !d.hasDate;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + (hasDate ? date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : by) + ")";
    }
}
