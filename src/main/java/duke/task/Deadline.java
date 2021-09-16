package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class represents tasks that need to be done before a
 * specific date/time e.g., submit report by 11/10/2019 5pm.
 *
 * @author Chng Zi Hao
 */
public class Deadline extends Task {
    private LocalDate dueDate;
    private LocalTime dueTime;

    /**
     * Constructor for Deadline.
     *
     * @param description The description of the deadline.
     * @param dueDate The due date of deadline.
     * @param dueTime The due time of deadline.
     */
    public Deadline(String description, LocalDate dueDate, LocalTime dueTime) {
        super(description);
        this.dueDate = dueDate;
        this.dueTime = dueTime;
    }

    /**
     * Formats dueDate and dueTime to an appropriate format to be printed.
     *
     * @return Formatted DateTime.
     */
    private String formatDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String date = dueDate.format(formatter);
        return date + " " + dueTime.toString();
    }

    @Override
    public boolean hasDate(LocalDate date) {
        return dueDate.equals(date);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDateTime() + ")";
    }

    @Override
    public String format() {
        return String.format("D | %s | %s %s", super.format(), dueDate, dueTime);
    }
}
