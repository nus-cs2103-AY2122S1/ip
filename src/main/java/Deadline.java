public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.description = description;
    }

    @Override
    public String toString() {
        return "\t[D]" + super.toString() + " (by: " + by + ")";
    }
}
