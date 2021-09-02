package edith.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import edith.commands.Instruction;
import edith.exceptions.DukeException;

/**
 * Represents an event task that takes in date and time.
 */
public class Events extends Task {
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Initialises Events with description, date and time.
     *
     * @param description Description of event.
     * @param date Date of event.
     * @param time Time of event.
     * @throws DukeException If user input date time is not in YYYY-MM-DD HH:mm format.
     */
    public Events(String description, String date, String time) throws DukeException {
        super(description, Instruction.EVENT);
        try {
            this.date = LocalDate.parse(date);
            this.time = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
        } catch (Exception e) {
            throw new DukeException("Sorry, I do not understand what you are saying... Please follow the format:\n"
                    + "event presentation /at 2021-09-09 14:15");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " " + time + ")";
    }
}
