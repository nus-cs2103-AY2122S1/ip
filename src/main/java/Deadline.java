public class Deadline extends Task {
    private String by;

    Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    Deadline(String name, String by, boolean isDone) {
        super(name, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
