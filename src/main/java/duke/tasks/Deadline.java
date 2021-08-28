package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class encapsulates a deadline with a deadline timing.
 */
public class Deadline extends Task {
    /** Deadline stored as a LocalDate object */
    private LocalDate deadline;

    public Deadline(String taskname, String deadline) {
        super(taskname);
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
}
