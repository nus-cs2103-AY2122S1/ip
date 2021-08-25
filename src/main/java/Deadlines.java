public class Deadlines extends Task {
    protected String by;

    public Deadlines(String description, String by) {
        super(description, false);
        this.by = by;
    }

    public Deadlines(String description, String isDone, String by) {
        super(description, isDone == "1");
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toSaveString() {
        return "D|" + super.toSaveString() + "|" + this.by;
    }
}
