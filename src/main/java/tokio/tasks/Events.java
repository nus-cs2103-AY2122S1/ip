package tokio.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import tokio.commands.Instruction;
import tokio.exceptions.DukeException;

/**
 * Represents an event task that takes in date and time.
 */
public class Events extends Task {
    protected String description;
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Initialises Events with description, date and time.
     *
     * @param description Description of event.
     * @param date Date of event in LocalDate type.
     * @param time Time of event in LocalTime type.
     */
    public Events(String description, LocalDate date, LocalTime time) {
        super(description, Instruction.EVENT);
        this.description = description;
        this.date = date;
        this.time = time;
    }

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
        this.description = description;
        try {
            this.date = LocalDate.parse(date);
            this.time = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
        } catch (Exception e) {
            throw new DukeException("I do not understand this format...\n" + "Rio, please follow this format:\n"
                    + "event {name} /at {yyyy-MM-dd} {HH:mm}");
        }
    }

    /**
     * Formats events for user display.
     *
     * @return Formatted Events task for user display.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " " + time + ")";
    }

    /**
     * Formats events for storage purposes.
     *
     * @return Formatted Events task for storage purposes.
     */
    @Override
    public String formatToStorage() {
        return "[E]" + super.formatToStorage() + " (at: " + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " " + time + ")";
    }

    /**
     * Compares two objects, if both objects are Events and have the same name, date and time,
     * then they will be considered equal.
     *
     * @param obj Object to be compared to.
     * @return True if objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Events) {
            Events e = (Events) obj;
            return this.description.toLowerCase(Locale.ROOT).equals(e.description)
                    && this.date.equals(e.date) && this.time.equals(e.time);
        } else {
            return false;
        }
    }
}
