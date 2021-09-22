package fan.cs2103t.duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a task of type "deadline".
 * <p>
 * This is a subclass of the <code>Task</code> class.
 * A task of type "deadline" has a specific deadline.
 */
public class Deadline extends Task {

    private final LocalDate deadline;

    /**
     * Constructs a task with the specified description and type "deadline",
     * with an initial completion status "not done".
     *
     * @param description the description of the "deadline" task.
     * @param deadline the deadline of the "deadline" task of type <code>LocalDate</code>
     */
    public Deadline(String description, LocalDate deadline) {
        super(description, TaskType.DEADLINE);
        this.deadline = deadline;
    }

    /**
     * Returns the description of this task with type "deadline" and its completion status.
     *
     * @return the detailed description of this "deadline" task.
     */
    @Override
    public String getDescriptionWithStatus() {
        return "[D]" + super.getDescriptionWithStatus() + " (by: "
                + deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH)) + ")";
    }

}
