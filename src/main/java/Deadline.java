public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return ("[D]" + super.toString() + " (by: " + this.by + ")");
    }

    public String toStorageString() {
        return ("D|" + super.getStatusNumber() + "|" + super.getDescription()
                + "|" + this.by);
    }
}
