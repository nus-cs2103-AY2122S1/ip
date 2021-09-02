package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * The type Deadline that has a task description and a deadline date to complete the task by.
 */
public class Deadline extends Task {

    /** Identifying tag 'D' for deadline task */
    private final String identifier = "D";
    /** Boolean flag to check if both Date and Time are specified by user */
    private final boolean isDateAndTime;
    /** Stores Date specified by user */
    private final LocalDate deadlineDate;
    /** Stores Date and Time if both are specified by user */
    private LocalDateTime deadlineDateTime;
    /** Stores Time specified by user */
    private LocalTime deadlineTime;

    /**
     * Instantiates a new Deadline.
     *
     * @param description the description for deadline task.
     * @param deadline    the deadline date.
     * @throws DateTimeParseException exception that is thrown if the user inputs an invalid date.
     */
    public Deadline(String description, String deadline) throws DateTimeParseException {
        super(description);
        isDateAndTime = false;
        this.deadlineDate = LocalDate.parse(deadline);
    }

    /**
     * Instantiates a new Deadline.
     *
     * @param description  the description for deadline task
     * @param deadlineDate the deadline date
     * @param deadlineTime the deadline time
     * @throws DateTimeParseException exception that is thrown if the user inputs invalid date and/or time
     */
    public Deadline(String description, String deadlineDate, String deadlineTime) throws DateTimeParseException {
        super(description);
        this.deadlineDate = LocalDate.parse(deadlineDate);
        this.deadlineTime = LocalTime.parse(deadlineTime);
        isDateAndTime = true;
        this.deadlineDateTime = LocalDateTime.of(this.deadlineDate, this.deadlineTime);
    }

    /**
     * Prints out Event task with an identifier, a done marker and the date/time specified.
     *
     * @return String.
     */
    @Override
    public String toString() {
        String result = "[" + identifier + "]";
        result += super.toString();
        result += (isDateAndTime
                ? " (by: " + this.deadlineDateTime.format(super.dateTimePattern) + ")"
                : " (by: " + this.deadlineDate.format(super.datePattern) + ")");
        return result;
    }

    @Override
    public String toDatabaseString() {
        String result = identifier + "|";
        result += getStatus() ? "1|" : "0|";
        result += getDescription() + "|";
        result += isDateAndTime ? (this.deadlineDate + "|" + this.deadlineTime) : this.deadlineDate;
        return result;
    }
}
