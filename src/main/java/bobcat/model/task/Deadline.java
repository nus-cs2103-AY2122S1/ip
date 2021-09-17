package bobcat.model.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import bobcat.exception.LogicException;

/**
 * A Task which represents a task with a deadline
 */
public class Deadline extends Task {
    protected LocalDate dueDate;

    /**
     * Returns a Deadline Object which represents a deadline in the TaskList
     * @param entry description of deadline
     * @param isComplete status of completion of deadline
     * @param dueDate due date of deadline
     */
    public Deadline(String entry, Boolean isComplete, String dueDate) {
        super(entry, isComplete);
        try {
            this.dueDate = LocalDate.parse(dueDate);
        } catch (DateTimeParseException e) {
            throw new LogicException("Cannot understand given date. Is it in \"yyyy-mm-dd\" format?");
        }
    }

    public Deadline(String entry, String dueDate) {
        this(entry, false, dueDate);
    }

    private String formatDateTime(LocalDate dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy")).trim();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDateTime(dueDate) + ")";
    }
}
