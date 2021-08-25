public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getFileString() {
        int i = this.isDone ? 1 : 0;
        return "D | " + i + " | " + this.description + "| " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + this.by + ")";
    }
}
