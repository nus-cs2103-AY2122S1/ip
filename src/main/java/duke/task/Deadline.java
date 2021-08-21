package duke.task;

import duke.exception.MissingArgumentException;
import duke.util.Parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline Class represents a task that needs to be done before a specific date/time.
 *
 * It contains information relating to the task:
 * - description
 * - isDone
 * - dueDate
 * - dueTime
 *
 * @author Benedict Chua
 */
public class Deadline extends Task {
    private LocalDate dueDate;
    private LocalTime dueTime;

    public Deadline(String description, String dueDate) {
        super(description);

        String[] dateInfo = dueDate.split(" ", 2);
        if (dateInfo.length < 2) {
            throw new MissingArgumentException("Date or Time", "Deadline");
        }
        this.dueDate = Parser.parseDate(dateInfo[0]);
        this.dueTime = Parser.parseTime(dateInfo[1]);
    }

    public Deadline(String completed, String description, String dueDate) {
        super(description);

        String[] dateInfo = dueDate.split(" ", 2);
        if (dateInfo.length < 2) {
            throw new MissingArgumentException("Date or Time", "Deadline");
        }
        this.dueDate = Parser.parseDate(dateInfo[0]);
        this.dueTime = Parser.parseTime(dateInfo[1]);

        if (completed.equals("1")) {
            super.markTaskAsDone();
        }
    }

    public String formatDate() {
        return dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public boolean onDate(String date) {
        return this.dueDate.equals(Parser.parseDate(date));
    }

    @Override
    public String saveAsString() {
        return super.formatString("D", String.format("%s %s", this.dueDate, this.dueTime));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s %s)", super.toString(), formatDate(), dueTime);
    }
}