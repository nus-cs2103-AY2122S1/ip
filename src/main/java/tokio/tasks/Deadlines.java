package tokio.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import tokio.commands.Instruction;
import tokio.exceptions.DukeException;

/**
 * Represents a Deadline task that takes in date.
 */
public class Deadlines extends Task {
    protected String description;
    protected LocalDate date;

    /**
     * Initialises a deadline object with description and date.
     *
     * @param description Name of deadline.
     * @param date Due date of deadline in String type.
     */
    public Deadlines(String description, String date) throws DukeException {
        super(description, Instruction.DEADLINE);
        this.description = description;
        try {
            this.date = LocalDate.parse(date);
        } catch (Exception e) {
            throw new DukeException("I do not understand this format...\n" + "Rio, please follow this format:\n"
                    + "deadline {name} /at {yyyy-MM-dd}");
        }
    }

    /**
     * Formats deadline for user display.
     *
     * @return Formatted deadline for user display..
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Formats deadline for storage purposes.
     *
     * @return Formatted deadline for storage purposes.
     */
    @Override
    public String formatToStorage() {
        return "[D]" + super.formatToStorage() + " (by: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Compares two objects, if both objects are Deadlines and have the same name and date,
     * then they will be considered equal.
     *
     * @param obj Object to be compared to.
     * @return True if objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Deadlines) {
            Deadlines d = (Deadlines) obj;
            return this.description.toLowerCase(Locale.ROOT).equals(d.description) && this.date.equals(d.date);
        } else {
            return false;
        }
    }
}
