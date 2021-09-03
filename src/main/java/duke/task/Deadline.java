package duke.task;

import java.time.LocalDate;

import duke.CommandList;

/**
 * Deadline class that stores a time when the task is due.
 */
public class Deadline extends Task {

    private LocalDate by;

    /**
     * Constructor for deadline.
     *
     * @param value String input from user.
     * @param by LocalDate for the due date.
     */
    public Deadline(String value, LocalDate by) {
        super(value);
        this.by = by;
    }

    @Override
    public LocalDate getTime() {
        return by;
    }

    @Override
    public CommandList getType() {
        return CommandList.DEADLINE;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

}
