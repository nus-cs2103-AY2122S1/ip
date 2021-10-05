package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * A task that need to be done before a certain date
 */
public class Deadline extends Task {
    /** Date that the task needs to be done before **/
    private LocalDate endDate;

    /**
     * Initializes a new Deadline
     *
     * @param name Name of Deadline
     * @param endDate Date to complete task by
     */
    public Deadline(String name, LocalDate endDate) {
        super(name);
        this.endDate = endDate;
    }

    /**
     * Initializes a new Deadline with done property
     *
     * @param name Name of Deadline
     * @param endDate Date to complete task by
     * @param isDone Whether task is done or not
     */
    public Deadline(String name, LocalDate endDate, boolean isDone) {
        super(name, isDone);
        this.endDate = endDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        String isDone = isDone() ? "✔" : " ";
        String prettyDate = endDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        return String.format("[D][%s] %s (by: %s)", isDone, getName(), prettyDate);
    }
}
