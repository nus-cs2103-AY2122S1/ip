public class Deadline extends Task {
    String by;

    Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
