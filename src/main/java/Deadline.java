public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String num, String description, String by) {
        this(description, by);
        this.isDone = !num.equals("0");
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String getFileString() {
        return String.format("D | %d | %s | %s", this.isDone ? 1 : 0, this.description, this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}