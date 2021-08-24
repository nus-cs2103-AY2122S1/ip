package ponyo.data.task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    // File operations
    @Override
    public String toStringInFile() {
        return "D - " + super.toStringInFile() + " - " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
