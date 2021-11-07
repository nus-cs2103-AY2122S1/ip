package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a task that have a deadline which is a string.
 * @author Dominic Siew Zhen Yu
 *
 */

public class Deadline extends Task {
    private String deadline;
    private String taskIndicator = "[D]";
    private LocalDateTime dateAndTime;
    /**
     * The constructor for the Deadlines class
     *
     * @param userInput the name of the deadlines
     * @param deadline date and time of deadline (YYYY-MM-DD HH:mm)
     */
    public Deadline(String userInput, String deadline, boolean newInput) {
        super(userInput);
        if (newInput) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime date = LocalDateTime.parse(deadline, formatter);
            this.deadline = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
            this.dateAndTime = date;
        } else {
            this.deadline = deadline;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
            LocalDateTime date = LocalDateTime.parse(deadline, formatter);
            this.dateAndTime = date;
        }

    }
    /**
     * returns true if the deadline is on the same date as the comparisonDate, and false
     * otherwise.
     * @param comparisonDate the date that is used to compare
     * @return true if the task's deadline is on the given date
     */
    public boolean isDate(String comparisonDate) {
        LocalDate comparison = LocalDate.parse(comparisonDate);
        LocalDate deadlineDate = this.dateAndTime.toLocalDate();

        return comparison.isEqual(deadlineDate);
    }
    /**
     * returns the dateAndTime attribute of the Deadline object.
     * @return the LocalDateTime object of the Deadline object
     */
    public LocalDateTime getLocalDateTime() {
        return this.dateAndTime;
    }
    /**
     * prints the name of the deadlines task.
     * @return the string representation of the deadlines
     */
    public String printName() {
        return taskIndicator + " " + super.printName() + " (by: " + this.deadline + ")";
    }
}

