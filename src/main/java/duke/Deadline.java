package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a task that have a deadline which is a string.
 * @author Dominic Siew Zhen Yu
 *
 */
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Deadline extends Task {
    private String deadline;
    private LocalDateTime dateAndTime;
    String TASKINDICATOR = "[D]";


    /**
     * The constructor for the Deadlines class with the userInput (which refers to the name of the task)
     * and the deadline parameter which is the deadline of the deadlines task.
     *
     * @param userInput the name of the deadlines
     * @param deadline the time you have to complete the Deadlines
     */

    public Deadline(String userInput, String deadline, boolean newInput) {
        super(userInput);

        if (newInput) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime date = LocalDateTime.parse(deadline, formatter);
            this.deadline = date.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
            this.dateAndTime = date;
        } else {
            this.deadline = deadline;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
            LocalDateTime date = LocalDateTime.parse(deadline, formatter);
            this.dateAndTime = date;
        }

    }

    public boolean isDate(String comparisonDate) {
        LocalDate comparison = LocalDate.parse(comparisonDate);
        LocalDate deadlineDate = this.dateAndTime.toLocalDate();

        return comparison.isEqual(deadlineDate);
    }

    /**
     * the printName() method prints the name of the deadlines task.
     *
     * @return the string representation of the deadlines
     */

    public LocalDateTime getLocalDateTime() {
        return this.dateAndTime;
    }

    public String printName() {
        return TASKINDICATOR + " " + super.printName() + " (by: " + this.deadline + ")";
    }
}

