package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a task to be done with a deadline.
 */
public class Deadlines extends Task {
    /** When the task must be done by. */
    private LocalDate limit;

    /**
     * Constructs a Deadlines object.
     *
     * @param description Description of the task.
     * @param limit When the task must be done by.
     */
    public Deadlines(String description, String limit) {
        super(description);
        this.limit = LocalDate.parse(limit.replace("/", "-"));
    }

    @Override
    public String saveData() {
        return "deadline " + super.saveData() + " /by " + this.limit.toString();
    }

    private String dateConverter() {
        return this.limit.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    /**
     * Returns the string form of the Deadlines object.
     *
     * @return The string form of the Deadlines.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateConverter() + ")";
    }
}
