package tasks;

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
    public Deadline(String description, Boolean isDone) {
        super(description, isDone, "D");
        this.description = description;
    }

    /**
     * Returns the status and description of the deadline task.
     *
     * @return a string representation of the deadline task.
     */
    public String getTask() {
        String[] splitted = description.split("/by ", 2);
        String text = splitted[0].trim();
        deadline = splitted[1].trim();
        LocalDateTime dateTime = LocalDateTime.parse(deadline);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm a");

        return "[D]" + "[" + super.getStatusIcon() + "] " + text + " (by: " + dateTime.format(formatter) + ")";
    }

}
