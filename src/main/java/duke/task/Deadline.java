package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private LocalDate deadline;

    /**
     * Creates a new Task object with a deadline attached to it.
     *
     * @param description The description of the task.
     * @param deadline The deadline of the task.
     * @throws DateTimeParseException If the deadline input is not a valid date
     */
    public Deadline(String description, String deadline) throws DateTimeParseException {
        super(description);
        this.deadline = LocalDate.parse(deadline);
    }

    /** Returns the deadline of the task as a LocalDate object */
    public LocalDate getDeadline() {
        return this.deadline;
    }

    /** Returns the deadline of the task as a String */
    private String deadlineToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return this.deadline.format(formatter);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadlineToString());
    }
}
