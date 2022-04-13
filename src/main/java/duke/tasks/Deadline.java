package duke.tasks;

import duke.dukeException.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A deadline task that extends from Task.
 *
 * @author Erwin Quek
 * @version CS2103 AY21/22 Sem 1
 */
public class Deadline extends Task{
    private String dateTime;
    private final LocalDateTime dt;

    /**
     * Constructor for Deadline.
     * @param description Takes in a description of Deadline task.
     */
    public Deadline(String description) throws DukeException {
        super(description.substring(0, description.indexOf("/by ")).trim());
        this.dateTime = description.substring(description.indexOf("/by ") + 4).trim();
        if (dateTime.length() < 16) {
            String message = "\nOOPS!!! Please follow this format: " +
                    "\n deadline {task} /by {YYYY-MM-DDTHH:MM}";
            throw new DukeException(message);
        }
        this.dt = LocalDateTime.parse(this.dateTime);  //eg LocalDateTime.parse("2015-02-20T06:30");
    }

    /**
     * Returns a string representation of the deadline task details to be saved in duke.txt.
     * @return A string representation of the deadline tas.
     */
    @Override
    public String getTaskInfo() {
        return "D" + "|" + super.getZeroOrOne() + "|" + description + "|" + dateTime;
    }

    /**
     * Returns a String representation of Deadline task.
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        String formatDate = dt.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String formatTime = dt.format(DateTimeFormatter.ofPattern("hh:mm"));
        return "[D]" + super.toString() + " (by: " + formatDate +  ", " + formatTime + ")";
    }
}
