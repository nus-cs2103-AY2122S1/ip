public class Deadline extends Task {
    private final String by;

    public Deadline(String title, String by) {
        super(title);
        this.by = by;
    }

    static String getClassRepr() {
        return "D";
    }

    public String getBy() {
        return by;
    }

    public String toString() {
        return "[" + getClassRepr() + "]" + super.toString() + " (by: " + by + ")";
    }
}
