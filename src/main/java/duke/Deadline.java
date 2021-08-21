package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class extends Task.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructor for Deadline. This handles user input.
     *
     * @param input Raw input from the user.
     */
    public Deadline(String input) {
        super(input.split(" /")[0].substring(9));
        this.by = LocalDate.parse(input.split(" /")[1].substring(3));
    }

    /**
     * Constructor for Deadline. This is used when reading duke.txt file.
     *
     * @param description Description of the task activity.
     * @param by Time by which this task must be completed.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Getter for "by" in a formatted String.
     *
     * @return Formatted String that nicely displays LocalDate by into "MMM d yyyy".
     */
    private String getTime() {
        return by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * toString method for Deadline.
     *
     * @return Displays the task as [D], as well as "by".
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getTime() + ")";
    }
}