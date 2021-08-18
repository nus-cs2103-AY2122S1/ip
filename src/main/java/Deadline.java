public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String doneBy) {
        super(description);
        this.by = doneBy;
    }

    public String toString() {
        return "[D] " + super.toString() + " (by: " + by + ")";
    }
}
