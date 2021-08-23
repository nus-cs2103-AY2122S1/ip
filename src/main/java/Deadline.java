public class Deadline extends Task {
    String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getShortForm() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.by + ")";
    }
}
