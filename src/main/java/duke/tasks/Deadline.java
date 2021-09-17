package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class encapsulates a deadline with a deadline timing.
 */
public class Deadline extends Task {
    /** Deadline stored as a LocalDate object. */
    private LocalDate deadline;

    /**
     * Creates a deadline task with a given name and deadline.
     *
     * @param taskName Name of the deadline task.
     * @param deadline Deadline of the task with the format YYYY-MM-DD.
     */
    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * Returns a formatted deadline time.
     *
     * @return A string of the deadline time.
     */
    public String getDeadline() {
        return this.deadline
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String toString() {
        return "Deadline: " + super.toString() + " (by "
                + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline) {
            return ((Deadline) obj).getTaskName().equals(this.getTaskName())
                    && ((Deadline) obj).deadline.equals(this.deadline);
        }
        return false;
    }
}
