package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {

    protected String type;
    protected LocalDate time;

    public Deadline(String description, LocalDate time) {
        super(description);
        this.type = "Deadline";
        this.time = time;
    }

    /**
     * Returns the string representation of the deadline task in the text file
     * @return string representation of the deadline task in the text file
     */
    public String addToFile() {
        return "D | 0 | " + this.description + " | " + this.time;
    }

    /**
     * Returns the string representation of the task to be shown in the Duke app
     * @return the string representation of the task to be shown in the Duke app
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}