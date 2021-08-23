package model.task;

public class Deadline extends Task {
    protected String dueDate;
    public Deadline(String entry, Boolean status, String startTime) {
        super(entry, status);
        this.dueDate = startTime;
    }

    public Deadline(String entry, String startTime) {
        this(entry, false, startTime);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }
}
