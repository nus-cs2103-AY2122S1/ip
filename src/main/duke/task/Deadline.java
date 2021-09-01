package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    protected String by;

    protected LocalDate date;
    private boolean hasDate;

    /**
     * Constructs a deadline instance with description and due time.
     * @param description description of deadline
     * @param by due time. If given string is in format xxxx-xx-xx(x is number), a LocalDate instance will be created to
     *          store the date
     */
    public Deadline(String description, String by) {
        super(description);
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
     * @param description description of deadline
     * @param by due time. If given string is in format xxxx-xx-xx(x is number), a LocalDate instance will be created to
     *          store the date
     * @param isDone task status
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        try {
            date = LocalDate.parse(by);
            hasDate = true;
            this.by = by;
        } catch (DateTimeParseException e) {
            hasDate = false;
            this.by = by;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                (hasDate ? date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : by) + ")";
    }

    @Override
    public String populateSaveData() {
        return "D | " + (this.isDone ? 1 : 0) + " | " + this.description + " | " + this.by;
    }
}
