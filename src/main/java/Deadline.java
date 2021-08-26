public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String getTypeIndicator() {
        return "[D]";
    }

    @Override
    public String toFileRecord() {
        return String.format("E | %d | %s | %s",
                this.isDone ? 1 : 0 , this.description, this.by);
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}