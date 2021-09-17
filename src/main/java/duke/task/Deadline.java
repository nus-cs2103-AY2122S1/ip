package duke.task;

import java.time.LocalDateTime;

import duke.DateTime;

public class Deadline extends Task {
    protected LocalDateTime time;

    /**
     * Constructs a new Deadline object.
     *
     * @param description Deadline description.
     * @param time Deadline time.
     */
    public Deadline(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    /**
     * Constructs a new Deadline object.
     *
     * @param description Deadline description.
     * @param time Deadline time.
     * @param isCompleted Deadline completion status.
     */
    public Deadline(String description, LocalDateTime time, boolean isCompleted) {
        super(description, isCompleted);
        this.time = time;
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        String timeStr = DateTime.stringify(this.time);

        return "[D]" + super.toString() + " (by: " + timeStr + ")";
    }
}
