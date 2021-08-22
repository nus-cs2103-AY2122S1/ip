package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a duke.task to be done with a deadline.
 */
public class Deadlines extends Task {
    /** When the duke.task must be done by. */
    private LocalDate limit;

    /**
     * Constructor of a duke.task.Deadlines object.
     *
     * @param description Description of the duke.task.
     * @param limit When the duke.task must be done by.
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
     * Returns the string form of the duke.task.Deadlines object.
     *
     * @return The string form of the duke.task.Deadlines.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateConverter() + ")";
    }
}
