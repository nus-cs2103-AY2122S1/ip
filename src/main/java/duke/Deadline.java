package duke;

import java.time.LocalDate;

/**
 * Class for Deadline tasks.
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Constructor for Deadline object.
     * @param description String representing description of the deadline.
     * @param deadline LocalDate of the deadline.
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Getter for deadline.
     * @return LocalDate representing the deadline.
     */
    public LocalDate getDeadline() {
        return this.deadline;
    }
}
