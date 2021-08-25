public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public Deadline markDone() {
        super.markDone();
        return this;
    }

    @Override
    public String formatChnage() {
        return super.formatChnage();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
