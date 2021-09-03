package duke.task;

import java.time.LocalDateTime;

class Deadline extends Task {
    /**
     * Date and time representation of deadline
     */
    private LocalDateTime by;

    /**
     * Constructor for a `Deadline` task.
     *
     * @param isDone      Indicates whether the task has been completed.
     * @param description Task description.
     * @param by          Date and time representation of deadline.
     */
    public Deadline(boolean isDone, String description, String by) {
        super(isDone, description);
        this.by = LocalDateTime.parse(by, INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public String saveAsString() {
        return 1 + "\n" + super.saveAsString() + "\n" + by.format(INPUT_FORMAT) + "\n";
    }
}
