package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * tasks.Deadline class for tasks that need to be done before a specific date/time
 *
 * @author: Chen Hsiao Ting
 */

public class Deadline extends Task {
    private String description;
    private String deadline;

    public Deadline(String description, Boolean isDone) {
        super(description, isDone, "D");
        this.description = description;
    }


    /**
     * Print the status and description of the deadline task.
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
