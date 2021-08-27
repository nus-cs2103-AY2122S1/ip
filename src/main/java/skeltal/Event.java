package skeltal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class is a child of task that has the additional attribute of time,
 * to allow the storing of an Event timing.
 */
public class Event extends Task {
    private String time;

    /**
     * A constructor that initialises a Event object.
     * @param rawTime A semi-processed string from the parser which contains
     *                The task and the time. e.g "Task /time".
     * @throws SkeltalException If the time description is not found.
     */
    public Event(String rawTime) throws SkeltalException {
        super(rawTime.split("/", 2)[0]);
        String[] procTime = rawTime.split("/", 2);
        String time;

        if (procTime.length == 1) {
            throw new SkeltalException("OOPS! The description of an event cannot be empty!");
        }

        try {
            LocalDate date = LocalDate.parse(procTime[1], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            time = date.format(DateTimeFormatter.ofPattern("dd MMM yy"));
        } catch (DateTimeParseException e) {
            time = procTime[1];
        }
        this.time = time;
    }

    private String formatTime() {
        return "(" + this.time + ")";
    }

    /**
     * A method that overrides the store() function in the Task parent class,
     * to include the task type and time description of the Event object.
     *
     * @return A String representation of the Event object that is readable by the loader.
     */
    @Override
    public String store() {
        return "E | " + super.store() + "| " + time;
    }

    /** Returns a String representation of the Event object, for printing purposes.
     * Eg "[E][ ] Task (time)".
     * @return A string representation of the Event object for printing.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + formatTime();
    }
}
