package yoyo.task;

import static yoyo.utility.Constant.WHITESPACE;

import java.time.LocalDateTime;

import yoyo.utility.Constant;

/**
 * A subclass of duke.task.Task of deadline type.
 */
public class Deadline extends Task {
    private LocalDateTime datetime;

    /**
     * Constructor for the Deadline class with name and datetime parameters.
     *
     * @param name     Name of Deadline.
     * @param datetime Datetime of Deadline.
     */
    public Deadline(String name, LocalDateTime datetime) {
        super(name);
        this.datetime = datetime;
    }

    /**
     * Constructor for the Deadline class with name, datetime and isDone parameters.
     *
     * @param name     Name of Deadline.
     * @param datetime Datetime of Deadline.
     * @param isDone   Completion status of task.
     */
    public Deadline(String name, LocalDateTime datetime, boolean isDone) {
        super(name, isDone);
        this.datetime = datetime;
    }

    /**
     * Constructor for the Deadline class with name, datetime, isDone and tags parameters.
     *
     * @param name     Name of Deadline.
     * @param datetime Datetime of Deadline.
     * @param isDone   Completion status of task.
     * @param tags     Tags of the task.
     */
    public Deadline(String name, LocalDateTime datetime, boolean isDone, String[] tags) {
        super(name, isDone, tags);
        this.datetime = datetime;
    }

    /**
     * Returns a string containing time info of this task.
     *
     * @return String containing time info of this task.
     */
    public String showTimeInfo() {
        return "(by: " + datetime.toString().replace('T', ' ') + ")";
    }

    /**
     * Produces a string containing task's status.
     *
     * @return a string containing task's status.
     */
    @Override
    public String showStatus() {
        String resultString = printType()
                + printCompletionStatus()
                + WHITESPACE
                + name
                + WHITESPACE
                + showTimeInfo();

        if (tags.size() == 0) {
            return resultString;
        }

        return resultString + WHITESPACE + showTags();
    }

    /**
     * Produces a string containing task's status in write format.
     *
     * @return a string containing task's status in write format.
     */
    @Override
    public String showStatusWrite() {
        return this.printType()
                + this.printCompletionStatus()
                + Constant.COMMA_SEPARATOR + this.name
                + Constant.COMMA_SEPARATOR + this.datetime
                + this.showTagsWriteFormat();
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
        //Test code should be passing the right argument into this method
        assert o instanceof Deadline;

        @SuppressWarnings("unchecked")
        Deadline otherDeadline = (Deadline) o;
        return this.isDone == otherDeadline.isDone
                && this.name.equals(otherDeadline.name)
                && this.datetime.equals(otherDeadline.datetime);
    }
}
