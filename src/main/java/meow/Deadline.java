package meow;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a specific task (an event) containing the description
 * and the deadline of the task that the user wants
 * to add in his or her todo list.
 */
public class Deadline extends Task {
    protected LocalDate date;
    protected String time;

    /**
     * A public constructor to initialize a meow.Deadline object.
     */
    public Deadline(String description, LocalDate date, String time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    public String getDeadlineTime() {
        return this.time;
    }

    public String getDeadlineDate() {
        return this.date.toString();
    }

    /**
     * Returns the string representation of the meow.Deadline object.
     *
     * @return The string representation of the meow.Deadline object.
     */
    @Override
    public String toString() {
        return "D" + super.toString() + " | " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + time;
    }
}
