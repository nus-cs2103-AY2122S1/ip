public class Deadline extends Task {
    private String by;

    public Deadline(String detail, String by) {
        super(detail, "D", by);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
