package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    private String deadline;
    private LocalDate deadlineTime;

    Deadline(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
        this.deadlineTime = setDeadlineTime(deadline);
    }

    /**
     * Returns the deadline of the Deadline object.
     *
     * @return The deadline of the Deadline object. If the original deadline
     * is given in a YYYY-MM-DD format, the deadline will be returned in a MMM d yyyy format.
     */
    public String getDeadline() {
        if (deadlineTime == null) {
            return this.deadline;
        } else {
            return this.deadlineTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
    }

    private LocalDate setDeadlineTime(String deadline) {
        try {
            LocalDate date = LocalDate.parse(deadline);
            return date;
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Returns the Deadline in a string format suitable for storing in file.
     *
     * @return String of the Deadline object in the correct format for storing in file.
     */
    @Override
    public String saveTaskToFile() {
        return this.getTypeOfTask() + "||" + this.getStatusIcon() + "||"
                + this.getDescription() + "||" + this.getDeadline();
    }

    /**
     * Returns the type of task. Always return "D" which stands of the "D" in DeadLine.
     *
     * @return "D".
     */
    @Override
    public String getTypeOfTask() {
        return "D";
    }

    /**
     * Returns the Deadline object in a string format.
     *
     * @return String in the format of "[D][marked as done?]_deadlineDescription_(by:_deadline)."
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)", this.getTypeOfTask(),
                this.getStatusIcon(), this.getDescription(), this.getDeadline());
    }
}
