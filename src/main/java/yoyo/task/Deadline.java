package yoyo.task;

import yoyo.utility.Separator;

import java.time.LocalDateTime;

/**
 * A subclass of duke.task.Task of deadline type.
 */
public class Deadline extends Task {
    private LocalDateTime datetime;

    public Deadline(String name, LocalDateTime datetime) {
        super(name);
        this.datetime = datetime;
    }

    public Deadline(String name, LocalDateTime datetime, boolean isDone) {
        super(name, isDone);
        this.datetime = datetime;
    }

    @Override
    public String showStatus() {
        String status = super.showStatus();
        return status + " (by: " + datetime.toString().replace('T', ' ') + ")";

    }

    @Override
    public String showStatusWrite() {
        return this.printType() + this.printCompletionStatus()
                + Separator.SEPARATOR + this.name
                + Separator.SEPARATOR + this.datetime;
    }

    @Override
    public String printType() {
        return "[D]";
    }

    @Override
    public boolean equals(Object o) {
        @SuppressWarnings("unchecked")
        Deadline otherDeadline = (Deadline) o;
        return this.isDone == otherDeadline.isDone
                && this.name.equals(otherDeadline.name)
                && this.datetime.equals(otherDeadline.datetime);
    }
}
