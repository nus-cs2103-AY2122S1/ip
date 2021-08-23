public class Deadline extends Task {

    protected String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    @Override
    public String getRecordString() {
        int done = this.isDone ? 1 : 0;
        String record = String.format("D | %d | %s | %s", done, this.name, this.by);
        return record;
    }
}
