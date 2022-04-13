package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Dr-Octavius
 *
 * Class that represents a Event Task.
 * Parent Class: Task
 *
 * @version 1.0
 */
public class Deadline extends Task {

    private final LocalDate date;

    /**
     * Class Constructor that takes 3 parameters
     *
     * @param description Deadline Task description
     * @param by Actual Deadline of Dealine Task
     */
    public Deadline(boolean state, String description, String by) {
        super(TASKTYPE.D, description, state);
        date = LocalDate.parse(by);
    }

    /**
     * Class Constructor that takes 2 parameters
     *
     * @param description Deadline Task description
     * @param by Actual Deadline of Dealine Task
     */
    public Deadline(String description, String by) {
        super(TASKTYPE.D, description);
        date = LocalDate.parse(by);
    }

    /**
     * Returns when the Task has to be done
     *
     * @return String description of where Task will be done
     */
    @Override
    public LocalDate getDate() {
        assert date != null : "Deadline was created without a end date";
        return date;
    }

    /**
     * String representation of a Deadline
     *
     * @return Summary of Deadline information
     */
    @Override
    public String toString() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MMM d yyyy");
        String data = date.format(df);
        return super.toString().concat(" (by: ".concat(data).concat(")"));
    }
}


