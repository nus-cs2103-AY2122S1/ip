public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getTime() {
        return by;
    }

    public String formatSave() {
        return "D | "  + ((super.isDone) ? "1 |" : "0 |") + " " + super.getDescription() + " | " + getTime();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}