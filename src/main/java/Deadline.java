public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public static Deadline splitDeadline(String input) {
        String[] partsOfDeadline = input.split("/by ");
        String deadlineContent = partsOfDeadline[0].substring(9);
        String by = partsOfDeadline[1];
        return new Deadline(deadlineContent, by);
    }

    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: "
                + this.by
                + ")";
    }
}
