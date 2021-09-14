package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Encapsulates a deadline task with a deadline.
 */
public class Deadline extends Task {
    private LocalDateTime deadlineDate;

    /**
     * Constructs a Deadline task object.
     *
     * @param description Task description.
     * @param isCompleted Completion status of task.
     * @param deadlineDate Deadline of task.
     */
    public Deadline(String description, Boolean isCompleted, LocalDateTime deadlineDate, ArrayList<String> tags) {
        super(description, isCompleted, tags);
        this.deadlineDate = deadlineDate;
    }

    @Override
    public String typeIcon() {
        return "[D]";
    }

    @Override
    public String toString() {
        return super.toString()
                + " (by: "
                + deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"))
                + ")" + super.getTags();
    }

    @Override
    public String toFileString() {
        return super.toString() + " (by: " + deadlineDate + ")" + super.getTags();
    }
}
