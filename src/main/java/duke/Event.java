package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class extends Task.
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Constructor for Event. This handles user input.
     *
     * @param input Raw input from the user.
     */
    public Event(String input) {
        super(input.split(" /")[0].substring(6));
        this.at = LocalDate.parse(input.split(" /")[1].substring(3));
    }

    /**
     * Constructor for Event. This is used when reading duke.txt file.
     *
     * @param description Description of the task activity.
     * @param at Time at which this task will take place.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Getter for "at" in a formatted String.
     *
     * @return Formatted String that nicely displays LocalDate by into "MMM d yyyy".
     */
    private String getTime() {
        return at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * toString method for Event.
     *
     * @return Displays the task as [E], as well as "at".
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getTime() + ")";
    }
}