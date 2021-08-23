package duke.tasks;

import duke.tasks.Task;

import java.time.LocalDate;

public class Deadline extends Task {
    protected final LocalDate dateBy;

    public Deadline(String description, LocalDate dateBy) {
        super(description);
        this.dateBy = dateBy;
        this.isDone = false;
    }

    public Deadline(String description, LocalDate dateBy, boolean isDone) {
        super(description);
        this.dateBy = dateBy;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + description + " (by: " +
                dateBy.getDayOfMonth() + " " + dateBy.getMonth().toString() + " " + dateBy.getYear() + ")";
    }

    @Override
    public String getStatusString() { return "D@" + (isDone ? 1 : 0) + "@" + this.description + "@" + this.dateBy.toString(); }

}
