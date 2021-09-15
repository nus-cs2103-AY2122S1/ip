package misaki.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a start and end dates.
 *
 * @author Chen Hsiao Ting
 * @version CS2103T AY21/22 Semester 1
 */
public class Event extends Task {
    private String description;
    private String deadline;

    /**
     * A constructor for Event.
     *
     * @param description User input task description.
     * @param deadline User input task start and end dates.
     * @param isDone Status of the task.
     */
    public Event(String description, String deadline, Boolean isDone) {
        super(description + " /at " + deadline, isDone, "E");
        this.description = description;
        this.deadline = deadline;
    }

    /**
     * Returns the status and description of the event task.
     *
     * @return String representation of the event task.
     */
    public String getTask() {
        LocalDateTime dateTime = LocalDateTime.parse(deadline);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm a");

        return "[E]" + "[" + super.getStatusIcon() + "] " + description + " (at: " + dateTime.format(formatter) + ")";
    }
}
