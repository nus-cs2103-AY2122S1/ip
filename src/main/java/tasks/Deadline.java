package tasks;

public class Deadline extends Task {

    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description);
        this.by = by;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[D]" + (this.isDone ? "[X] " : "[ ] ") + this.description + " (by: " + this.by + ")";
    }

    @Override
    public String toStorage() {
        return ("D%" + this.isDone + "%" + this.description + "%" + this.by + "\n");
    }
}
