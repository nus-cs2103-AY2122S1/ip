package duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a subclass of Task that can be added to the List.
 * It is annotated by D in the list.
 * Contains additional field of deadline of type LocalDate.
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Constructor for Deadline class.
     *
     * @param name Name of the task.
     * @throws DukeDeadlineException If the name is an empty String.
     * @throws DateTimeException If input date is NOT formatted in yyyy-mm--dd.
     */
    public Deadline(String name) throws DukeDeadlineException, DateTimeException {
        super(name.substring(0, name.indexOf(" /by ") + 1));
        this.deadline = LocalDate.parse(name.substring(name.indexOf(" /by ") + 5));
        if (name.equals("")) {
            throw new DukeDeadlineException();
        }
    }

    /**
     * Overloaded constructor for Deadline class.
     * Used when loading the data file.
     *
     * @param input An array of Strings with type of Task, name, done, date.
     */
    public Deadline(String[] input) {
        super(input[2].substring(0, input[1].length() - 1), input[1].equals("T") ? true : false);
        this.deadline = LocalDate.parse(input[3]);
    }

    /**
     * Returns the deadline of the Deadline Object.
     *
     * @return deadline of the Deadline Object in MMM d yyyy format.
     */
    public String getDate() {
        return this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toDataString() {
        return "D|" + super.toDataString() + "|" + this.deadline;
    }
}
