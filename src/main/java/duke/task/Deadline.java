package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import duke.exception.MissingArgumentException;
import duke.util.Parser;

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

    /**
     * Constructs a new Deadline task from the given description and dueDate.
     *
     * @param description String of the task description.
     * @param dueDate String of the given due date and time.
     */
    public Deadline(String description, String dueDate) {
        super(description);

        String[] dateInfo = dueDate.split(" ", 2);
        if (dateInfo.length < 2) {
            throw new MissingArgumentException("Date or Time", "Deadline");
        }
        this.dueDate = Parser.parseDate(dateInfo[0]);
        this.dueTime = Parser.parseTime(dateInfo[1]);
    }

    /**
     * Constructs a Deadline task from an existing task description, dueDate and completion status.
     * Used when loading from a save file.
     *
     * @param completed String indicating the status of completion: 1 if done, 0 if not.
     * @param description String of the task description.
     * @param dueDate String of the given due date and time.
     */
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

    /**
     * Formats date into a String for printing.
     *
     * @return String of the formatted date in the form Mmm d yyyy.
     */
    private String formatDate() {
        return dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public boolean isOnDate(String date) {
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
