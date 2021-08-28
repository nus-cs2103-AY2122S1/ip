package yoyo.task;

import java.time.LocalDateTime;

import yoyo.utility.Separator;


/**
 * A subclass of duke.task.Task of deadline type.
 */
public class Deadline extends Task {
    private LocalDateTime datetime;

    /**
     * Constructor for the Deadline class with name and datetime parameters.
     *
     * @param name Name of Deadline.
     * @param datetime Datetime of Deadline.
     */
    public Deadline(String name, LocalDateTime datetime) {
        super(name);
        this.datetime = datetime;
    }

    /**
     * Constructor for the Deadline class with name, datetime and isDone parameters.
     *
     * @param name Name of Deadline.
     * @param datetime Datetime of Deadline.
     */
    public Deadline(String name, LocalDateTime datetime, boolean isDone) {
        super(name, isDone);
        this.datetime = datetime;
    }

    /**
     * Produces a string containing task's status.
     *
     * @return a string containing task's status.
     */
    @Override
    public String showStatus() {
        String status = super.showStatus();
        return status + " (by: " + datetime.toString().replace('T', ' ') + ")";

    }

    /**
     * Produces a string containing task's status in write format.
     *
     * @return a string containing task's status in write format.
     */
    @Override
    public String showStatusWrite() {
        return this.printType() + this.printCompletionStatus()
                + Separator.SEPARATOR + this.name
                + Separator.SEPARATOR + this.datetime;
    }

    /**
     * Returns a status string indicating type of task.
     *
     * @return An indicator string for the type of task.
     */
    @Override
    public String printType() {
        return "[D]";
    }


    /**
     * Defines how to compare two Deadline instances.
     *
     * @param o Object to be compared with.
     * @return A boolean.
     */
    @Override
    public boolean equals(Object o) {
        @SuppressWarnings("unchecked")
        Deadline otherDeadline = (Deadline) o;
        return this.isDone == otherDeadline.isDone
                && this.name.equals(otherDeadline.name)
                && this.datetime.equals(otherDeadline.datetime);
    }
}
