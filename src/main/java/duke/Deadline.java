package duke;

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
     * Constructor for Dealine.
     * @param description Takes in a description of Deadline task
     */
    public Deadline(String description) {
        super(description.substring(0, description.indexOf("/by ")).trim());
        this.dateTime = description.substring(description.indexOf("/by ") + 4).trim();
        System.out.println(description);
        this.dt = LocalDateTime.parse(this.dateTime);  //eg LocalDateTime.parse("2015-02-20T06:30");
    }

    /**
     * A method to get deadline task info.
     * @return a string of the deadline task details to be saved in data
     */
    @Override
    public String getTaskInfo() {
        return "D" + "|" + super.getZeroOrOne() + "|" + description + "|" + dateTime;
    }

    /**
     * A method to get a String representation of Deadline task.
     * @return a string of the Deadline task.
     */
    @Override
    public String toString() {
        String formatDate = dt.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String formatTime = dt.format(DateTimeFormatter.ofPattern("hh:mm"));
        return "[D]" + super.toString() + "(by: " + formatDate +  ", " + formatTime + ")";
    }
}
