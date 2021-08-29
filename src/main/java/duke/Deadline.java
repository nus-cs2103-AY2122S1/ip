package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for duke.Deadline, a child class of duke.Task.
 * @author Liew Jian Hong
 */

public class Deadline extends Task {
    /**
     * The date the deadline.
     */
    protected LocalDate date;

    /**
     * Constructor for a duke.Deadline task.
     *
     * @param desc String array consisting of parsed description.
     */
    public Deadline(String[] desc) {

        super(desc[1], Boolean.parseBoolean(desc[3]));
        this.date = LocalDate.parse(desc[2]);

    }

    /**
     * Converts the task into a string to be written into storage.
     *
     * @return String to be written into storage
     */
    @Override
    public String toWrite() {
        return "deadline--" + this.isDone + "--" + this.desc + "--" + this.date.toString() + "\n";
    }

    /**
     * Returns a string representation of the deadline.
     *
     * @return Return the type, completion status and description of the deadline.
     */
    @Override
    public String toString() {
        String dateString = this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        return "[D]" + super.toString() + " (by: " + dateString + ")\n";
    }
}
