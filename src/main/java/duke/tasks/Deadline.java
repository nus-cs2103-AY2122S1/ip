package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a deadline.
 *
 * @author Chen Hsiao Ting
 * @version CS2103T AY21/22 Semester 1
 */
public class Deadline extends Task {
    private String description;
    private String deadline;

    /**
     * A constructor for the Deadline.
     *
     * @param description User input task description.
     * @param deadline User input task deadline date.
     * @param isDone Status of the task.
     */
    public Deadline(String description, String deadline, Boolean isDone) {
        super(description + " /by " + deadline, isDone, "D");
        this.description = description;
        this.deadline = deadline;
    }

    /**
     * Returns the status and description of the deadline task.
     *
     * @return String representation of the deadline task.
     */
    public String getTask() {
        LocalDateTime dateTime = LocalDateTime.parse(deadline);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm a");

        return "[D]" + "[" + super.getStatusIcon() + "] " + description + " (by: " + dateTime.format(formatter) + ")";
    }
}
