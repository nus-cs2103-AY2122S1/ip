package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The class to represent a deadline.
 */
public class Deadline extends Task {

    /** representation of the deadline date */
    protected LocalDateTime by;

    /**
     * Constructor of Deadline class.
     *
     * @param name name of task
     * @param by date of deadline of the task
     */
    public Deadline(String name, LocalDateTime by) {
        super(name);
        assert(by != null);
        this.by = by;
    }

    /**
     * Constructor of Deadline class.
     *
     * @param name name of task
     * @param isDone whether the task has been completed
     * @param by date of deadline of the task
     */
    public Deadline(String name, boolean isDone, LocalDateTime by) {
        super(name, isDone);
        assert(by != null);
        this.by = by;
    }

    /**
     * Returns the string representation of the Deadline instance.
     *
     * @return the string representation of the Deadline instance
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm");
        return "[D]" + super.toString() + " (by: " + this.by.format(formatter) + ")";
    }

    /**
     * Return the string to be recorded into the data file of the Deadline instance.
     *
     * @return the string to be recorded of the Deadline instance
     */
    @Override
    public String getRecordString() {
        int done = this.isDone ? 1 : 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm");
        String record = String.format("D | %d | %s | %s", done, this.name, this.by.format(formatter));
        return record;
    }

    /**
     * Determine if two instances of Deadline are equal.
     *
     * @param obj the object to be used for comparison
     * @return boolean indicating if the two Deadline instances are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline) {
            Deadline dl = (Deadline) obj;
            return this.name.equals(dl.name) && this.by.equals(dl.by) && this.isDone == dl.isDone;
        }
        return false;
    }
}
