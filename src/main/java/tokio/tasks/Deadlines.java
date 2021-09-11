package tokio.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import tokio.commands.Instruction;

/**
 * Represents a Deadline task that takes in date.
 */
public class Deadlines extends Task {
    protected String description;
    protected LocalDate date;

    /**
     * Initialises a deadline object with description and date.
     *
     * @param description Description of deadline.
     * @param date Due date of deadline.
     */
    public Deadlines(String description, String date) {
        super(description, Instruction.DEADLINE);
        this.description = description;
        this.date = LocalDate.parse(date);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Deadlines) {
            Deadlines d = (Deadlines) obj;
            return this.description.equals(d.description) && this.date.equals(d.date);
        } else {
            return false;
        }
    }
}
