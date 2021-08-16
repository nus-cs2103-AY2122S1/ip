public class Deadline extends Task {
    private final String by;

    public Deadline(String title, String by) {
        super(title);
        this.by = by;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
