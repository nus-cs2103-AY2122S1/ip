package duke.task;

import duke.exception.DukeException;
import duke.util.Parser;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class represents tasks that need to be done before a
 * specific date/time e.g., submit report by 11/10/2019 5pm.
 *
 * @author Chng Zi Hao
 */
public class Deadline extends Task{
    private LocalDate dueDate;
    private LocalTime dueTime;

    /**
     * Constructor for Deadline.
     *
     * @param description The description of the deadline.
     * @param by The due date of deadline.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        String[] splitDateTime = by.split(" ", 2);
        if (splitDateTime.length != 2) {
            throw new DukeException("Missing Date/Time @_@");
        }
        this.dueDate = Parser.parseDate(splitDateTime[0]);
        this.dueTime = Parser.parseTime(splitDateTime[1]);
    }

    private String formatDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String date = this.dueDate.format(formatter);
        return date + " " + this.dueTime.toString();
    }

    @Override
    public boolean checkDate(LocalDate date) throws DukeException {
        return this.dueDate.equals(date);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.formatDateTime() + ")";
    }

    @Override
    public String format() {
        return String.format("D | %s | %s %s", super.format(), this.dueDate, this.dueTime);
    }
}
