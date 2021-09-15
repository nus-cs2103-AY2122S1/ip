package task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import exception.InvalidDateFormat;

/**
 * Deadline is a subclass of Task with a deadline.
 */
public class Deadline extends Task {

    private LocalDate date;

    DateTimeFormatter dayOutputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

    DateTimeFormatter dayInputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Initialises the description, deadline and isComplete status of task.
     *
     * @param description task description
     * @param deadline deadline of task
     * @param completed true if the task is completed
     * @throws InvalidDateFormat
     */
    public Deadline(String description, String deadline, String notes, boolean completed) throws InvalidDateFormat {
        super(description, notes, completed);
        try {
            this.date = LocalDate.parse(deadline,dayInputFormatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormat();
        }
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)\n%s", super.toString(),
                date.format(dayOutputFormatter), this.notes);
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String getDeadline() {
        return this.date.toString() ;
    }
}
