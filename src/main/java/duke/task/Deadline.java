package duke.task;

public class Deadline extends Task {
    private String by;
    private String tag = "D";

    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
    }

    @Override
    public String getAdditionalInfo() {
        return by;
    }

    @Override
    public String getTag() { return tag; }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}