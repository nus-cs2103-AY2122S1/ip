public class Deadline extends Task {
    protected final String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public Deadline markAsDone() {
        return new Deadline(this.getDescription(), this.getBy(), true);
    }

    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + this.getDescription() + "(by:" + this.getBy() + ")";
    }
}
