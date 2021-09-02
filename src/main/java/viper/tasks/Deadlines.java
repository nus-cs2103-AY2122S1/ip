package viper.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import viper.commands.Instruction;

/**
 * Represents a Deadline task that takes in date.
 */
public class Deadlines extends Task {
    protected LocalDate date;

    /**
     * Initialises a deadline object with description and date.
     *
     * @param description Description of deadline.
     * @param date Due date of deadline.
     */
    public Deadlines(String description, String date) {
        super(description, Instruction.DEADLINE);
        this.date = LocalDate.parse(date);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
