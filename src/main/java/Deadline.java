public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getReadableString() {
        String status = this.isDone ? "1" : "0";
        return "D | " + status + " | " + this.description + " | " + this.by  + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}