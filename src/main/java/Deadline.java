public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public char getTaskType() { return 'D'; }

    @Override
    public String getTime() {
        return this.by;
    }

    @Override
    public String toString() {
        return "  [D]" + super.toString() + " (by: " + by + ")";
    }
}
