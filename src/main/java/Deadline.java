public class Deadline extends Task {
    private final String by;

    public Deadline(String content, String by, boolean isDone) {
        super(content, isDone);
        this.by = by;
    }

    public Deadline(String content, String by) {
        this(content, by, false);
    }

    @Override
    public String dataFormat() {
        return String.format("D, %d, %s, %s\n", isDoneInt(), getContent(), by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}