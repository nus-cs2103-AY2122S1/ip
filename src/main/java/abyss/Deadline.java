package abyss;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "  [D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toFileEntry() {
        return "D | " + super.getIsDone() + " | " + super.description + " | " + this.by;
    }
}
