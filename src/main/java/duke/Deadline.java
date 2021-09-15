package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String timeBy;
    private LocalDate deadline;

    /**
     * Creates a Deadline object.
     *
     * @param description Description of the task.
     * @param timeBy      Due date of the task.
     */
    public Deadline(String description, String timeBy) {
        super(description);
        this.deadline = LocalDate.parse(timeBy);
        this.timeBy = timeBy;
    }

    /**
     * Returns the character representing the task type.
     *
     * @return A character representing the task type.
     */
    @Override
    public char getTaskType() {
        return 'D';
    }

    /**
     * Returns the due date of the task as a String.
     *
     * @return The due date of the task as a String
     */
    @Override
    public String getTime() {
        return this.timeBy;
    }

    /**
     * Returns the string version of the Deadline object.
     *
     * @return The string version of the Deadline object.
     */
    @Override
    public String toString() {
        String formattedDate = this.deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        return "  [D]" + super.toString() + " (by: " + formattedDate + ")";
    }
}
