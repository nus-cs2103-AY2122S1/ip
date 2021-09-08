package duke.task;

import java.time.LocalDate;

import duke.DukeDate;

/**
 * Represents a Deadline task.
 * Inherits from <code>Task</code> with the addition of a <code>time</code> attribute.
 */
public class Deadline extends Task {
    private LocalDate time;

    /**
     * Creates a new Deadline object.
     *
     * @param name Name of the task.
     * @param time Time the task is to be completed by.
     */
    public Deadline(String name, LocalDate time) {
        super(name, "D");
        this.time = time;
    }

    /**
     * Creates a new Deadline object, allows specifying whether it is complete.
     *
     * @param name Name of the task.
     * @param time Time the task is to be completed by.
     * @param isComplete Boolean representing whether task is completed.
     */
    public Deadline(String name, LocalDate time, boolean isComplete, String[] tags) {
        super(name, isComplete, "D", tags);
        this.time = time;
    }

    /**
     * Returns the time attribute.
     *
     * @return Time attribute of the Deadline object.
     */
    public LocalDate getTime() {
        return time;
    }

    /**
     * Returns the string to be used for saving the task on a file.
     *
         * @return String representing the Deadline.
     */
    @Override
    public String getSaveFormat() {
        return String.format(
                "%s|%s",
                super.getSaveFormat(),
                DukeDate.formatDateSave(time)
        );
    }
}
