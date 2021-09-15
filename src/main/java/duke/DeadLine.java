package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 * @author Nikki
 */

public class DeadLine extends Task {
    static final String DONE = "[D][X] ";
    static final String NOT_DONE = "[D][ ] ";
    private String deadLine;
    private LocalDateTime dateTime;

    /**
     * Creates a Deadline task object.
     *
     * @param task Name of the task.
     * @param deadLine Deadline of the task.
     */
    public DeadLine(String task, String deadLine) {
        super(task);
        this.deadLine = deadLine;
        timeFormatter(deadLine);
    }

    private void timeFormatter(String timeInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        dateTime = LocalDateTime.parse(timeInput, formatter);
    }

    /**
     * Returns a String with a different date and time format than user input.
     *
     * @return String representation of date and time after format change.
     */
    public String formatChanger() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h.mm a");
        return dateFormat.format(dateTime) + ", " + timeFormatter.format(dateTime);
    }

    /**
     * Returns a String representation of a Deadline task.
     *
     * @return String representation of this Deadline task.
     */
    @Override
    public String printTask() {
        String result = "";
        if (isComplete) {
            result = DONE;
        } else {
            result = NOT_DONE;
        }
        return result + task + " (by: " + formatChanger() + ")";
    }
}
