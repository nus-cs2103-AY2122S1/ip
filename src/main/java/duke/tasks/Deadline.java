package duke.tasks;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime by;


    /**
     * Instantiates a Deadline Object.
     *
     * @param description description of the task.
     * @param by due date of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns the string representation of this Deadline Object.
     *
     * @return String representation of Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(outputFormatter) + ")";
    }

    @Override
    public String toSaveString() {
        return "D" + super.toSaveString() + "|" + this.by.format(outputFormatter);
    }
}
