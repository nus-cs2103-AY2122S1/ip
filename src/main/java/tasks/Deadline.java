package tasks;

import tasks.Task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String time) {
        super(description);
        this.by = time;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by +")";
    }
}
