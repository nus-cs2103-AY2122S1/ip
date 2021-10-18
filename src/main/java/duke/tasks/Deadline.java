package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates the information for a Deadline object that contains the description, completion status and due date.
 */
public class Deadline extends Task {
    public static final String TAG = "D";
    private LocalDate dateOfDeadline;

    /**
     * Constructs a new Deadline object with the specified task description, due date and task status.
     *
     * @param description Description/Tile of the task.
     * @param dateOfDeadline Due date of the task.
     * @param isDone Completion status of the task.
     */
    public Deadline(String description, LocalDate dateOfDeadline, boolean isDone) {
        super(description, isDone);
        this.dateOfDeadline = dateOfDeadline;
    }

    @Override
    public String getTag() {
        return Deadline.TAG;
    }

    @Override
    public String getDueDate() {
        return this.dateOfDeadline.toString().trim();
    }

    @Override
    public void setDate(String date) throws DateTimeParseException {
        this.dateOfDeadline = LocalDate.parse(date);
    }

    @Override
    public String toString() {
        return "[" + Deadline.TAG + "]" + super.toString()
                + " (by: " + this.dateOfDeadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
