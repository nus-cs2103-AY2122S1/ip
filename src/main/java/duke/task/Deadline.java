package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a task that should be completed by a specific time.
 */
public class Deadline extends Task {
    /** Datetime deadline of task. */
    private LocalDateTime dateTime;

    /**
     * Constructs task with a deadline.
     *
     * @param done task is completed.
     * @param name task name.
     * @param dateTime deadline due date.
     */
    public Deadline(boolean done, String name, LocalDateTime dateTime) {
        super(done, name);
        this.dateTime = dateTime;
    }

    /**
     * Turns Deadline into a string for saving to memory.
     *
     * @return concise string representation of Deadline.
     */
    @Override
    public String encode() {
        return String.format("D|%s|%s", super.encode(), dateTime);
    }

    /**
     * Turns task into a human readable string form.
     *
     * @return string representation of Deadline.
     */
    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");
        return String.format("[D]%s (by: %s)", super.toString(), this.dateTime.format(dateTimeFormatter));
    }

    /**
     * Sets new Deadline.
     *
     * @param dateTime date time value to be changed to.
     */
    @Override
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
