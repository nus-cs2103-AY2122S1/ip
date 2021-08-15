public class Deadline extends Task{

    protected String by;

    Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    Deadline(String description, String by, Boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    public Deadline markAsDone() {
        return new Deadline(this.description, this.by, true);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
