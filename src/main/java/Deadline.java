public class Deadline extends Task {
    private final String by;

    public Deadline(String content, String by) {
        super(content);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}