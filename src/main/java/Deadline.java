public class Deadline extends Task {

    protected String by;

    
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String isDone, String description, String by) {
        super(description, isDone.equals("1"));
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toFileString() {
        return "D" + super.toFileString() + by;
    }
}