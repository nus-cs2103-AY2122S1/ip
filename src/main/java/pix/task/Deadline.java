package pix.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A deadline task to be met.
 */
public class Deadline extends Task {
    private LocalDate finishByDate;

    /**
     * Constructor for the deadline task.
     *
     * @param name Name of the task.
     * @param finishByDate Date to finish the task by.
     */
    public Deadline(String name, LocalDate finishByDate) {
        super(name, false);
        this.finishByDate = finishByDate;
    }

    /**
     * Constructor for the deadline task.
     *
     * @param name Name of the task.
     * @param done States whether the task is done.
     * @param finishByDate Date to finish the task by.
     */
    public Deadline(String name, boolean done, LocalDate finishByDate) {
        super(name, done);
        this.finishByDate = finishByDate;
    }

    /**
     * Retrieves the date of the deadline of the task.
     *
     * @return The deadline of the task.
     */
    public String getDate() {
        return finishByDate.toString();
    }

    /**
     * Formats the Pix.task into the format to save it in a text file.
     *
     * @return Returns the text representation of the task.
     */
    @Override
    public String getSaveName() {
        return isDone ? "D|1|" + name + "|" + finishByDate.toString() : "D|0|" + name + "|" + finishByDate.toString();
    }

    /**
     * String representation of a deadline task.
     *
     * @return Returns the string representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + finishByDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
