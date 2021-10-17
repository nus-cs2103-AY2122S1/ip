package bob.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import bob.exception.InvalidDateException;

/**
 * Represents a special type of task with a deadline, meaning the task must be completed before the specified date.
 */
public class Deadline extends Task {
    /** Deadline date of Deadline */
    private LocalDate deadline;

    /**
     * Constructor for a new Deadline task.
     * Calls the constructor of Task as well as the Deadline task is a task.
     *
     * @param description Description of the Deadline task.
     * @param deadline Deadline of the Deadline task (when the task must be completed by).
     * @throws InvalidDateException If the user inputs an invalid date for the deadline.
     */
    public Deadline(String description, String deadline) throws InvalidDateException {
        super(description);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }

    /**
     * Overrides the printTask() method in Task to print specifically the String representation of the Deadline task.
     * Calls the printTask() method in Task as well to form the general part of the String representation.
     *
     * @return String representation of the Deadline task.
     */
    @Override
    public String printTask() {
        String deadlineHeader = "[D] ";
        String formattedDate = deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return deadlineHeader + super.printTask() + " (by: " + formattedDate + ")";
    }
}
