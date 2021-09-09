package duke.tasks;

import duke.tasks.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private String description;
    private String startDate;
    private String endDate;

    /**
     * A constructor for the Event task.
     *
     * @param description user input task description.
     * @param isDone status of the task.
     */
    public Event(String description, String deadline, Boolean isDone) {
        super(description + " /from " + deadline.split(" ", 2)[0] + " /to " + deadline.split(" ", 2)[1], isDone, "E");
        this.description = description;
        this.startDate = deadline.split(" ", 2)[0];
        this.endDate = deadline.split(" ", 2)[1];
    }

    /**
     * Returns the status and description of the event task.
     *
     * @return a string representation of the event task.
     */
    public String getTask() {
        LocalDateTime startDateTime = LocalDateTime.parse(startDate);
        LocalDateTime endDateTime = LocalDateTime.parse(endDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm a");
        return "[E]" + "[" + super.getStatusIcon() + "] " + description + " (from: " + startDateTime.format(formatter) +
                " to " + endDateTime.format(formatter) + ")";
    }
}
