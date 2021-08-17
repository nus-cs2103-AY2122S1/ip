package model.task;

public class Deadline extends Task {
    protected String dueDate;
    public Deadline(String entry, String dueDate) {
        super(entry);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }
}
