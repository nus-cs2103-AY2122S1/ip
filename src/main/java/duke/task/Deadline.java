package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class that is used in order to create a new deadline task.
 *
 * @author mrmrinal
 */
public class Deadline extends Task {

    private static final String TASK_TYPE = "D";
    private final String by;

    /**
     * Constructor to instantiate new deadline object
     *
     * @param description Description of the deadline task
     * @param by Due date of the deadline task
     */
    public Deadline(String description, String by) {
        super(description);
        LocalDate time = LocalDate.parse(by);
        this.by = time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Constructor to instantiate new deadline object.
     *
     * @param description Description of the deadline task
     * @param by Due date of the deadline task
     * @param done Status of whether task was done or not
     */
    public Deadline(String description, String by, int done) {
        super(description, done);
        this.by = by;
    }

    /**
     * Method to return String that has been stored in the Arraylist.
     *
     * @return String stored in the ArrayList
     */
    @Override
    public String toString() {
        String taskDisplay = "[" + TASK_TYPE + "]";
        String timing = " (by: " + this.by + ")";
        return taskDisplay + super.toString() + timing;
    }

    @Override
    public String toStorageString() {
        int done = 0;
        if (this.isDone) {
            done = 1;
        }
        String newLine = "\n";
        return TASK_TYPE + SEPARATOR + done + SEPARATOR + this.description
                + SEPARATOR + this.by + newLine;
    }
}
