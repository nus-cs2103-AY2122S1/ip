package duke.tasks;

import duke.exceptions.DeadlineFormatException;
import duke.tasks.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String description;
    private String deadline;

    /**
     * A constructor for the deadline task.
     *
     * @param description user input task description.
     * @param isDone status of the task.
     */
    public Deadline(String description, String deadline, Boolean isDone) {
        super(description + " /by " + deadline, isDone, "D");
        this.description = description;
        this.deadline = deadline;
    }

    /**
     * Returns the status and description of the deadline task.
     *
     * @return a string representation of the deadline task.
     */
    public String getTask() {
        LocalDateTime dateTime = LocalDateTime.parse(deadline);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm a");

        return "[D]" + "[" + super.getStatusIcon() + "] " + description + " (by: " + dateTime.format(formatter) + ")";
    }

}
