package skeltal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class is a child of task that has the additional attribute of time,
 * to allow the storing of deadlines.
 */
public class Deadline extends Task {
    private String time;

    /**
     * A constructor that initialises a Deadline object.
     * @param rawTime A semi-processed string from the parser which contains
     *                The task and the time. e.g "Task /time".
     * @throws SkeltalException If the time description is not found.
     */
    public Deadline(String rawTime) throws SkeltalException {
        super(rawTime.split("/", 2)[0]);
        String[] procTime = rawTime.split("/", 2);
        if (procTime.length == 1) {
            throw new SkeltalException("OOPS! The description of a deadline cannot be empty!");
        }

        String time;
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
     * to include the task type and time description of the Deadline object.
     *
     * @return A String representation of the deadline object that is readable by the loader.
     */
    @Override
    public String store() {
        return "D | " + super.store() + "| " + this.time;
    }

     /** Returns a String representation of the Deadline object, for printing purposes.
      * Eg "[D][ ] Task (time)".
      * @return A string representation of the Deadline object for printing.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + formatTime();
    }
}
