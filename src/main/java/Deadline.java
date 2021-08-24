public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description, "D");
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String markDone() {
        this.isDone = true;
        return String.format("Nice! I've marked this task as done:\n  [D][X] %s", this.description);
    }
}
