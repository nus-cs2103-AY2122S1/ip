package biscuit.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * biscuit.tasks.Deadline class: For tasks that need to be done before a specific date/time
 * e.g., submit report by 11/10/2019 5pm
 */
public class Deadline extends Task {
    private final LocalDate date;

    public Deadline(String description, LocalDate date) {
        super(description, TaskType.DEADLINE);
        this.date = date;
    }

    public Deadline(boolean isDone, String description, LocalDate date) {
        super(isDone, description, TaskType.DEADLINE);
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return getTypeBox() + getCheckBox() + description + " (by: "
                + date.format(DateTimeFormatter.ofPattern("dd LLL yyyy")) + ")";
    }
}
