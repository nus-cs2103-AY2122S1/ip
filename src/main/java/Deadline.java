public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String descAndTime) {
        this(extractDesc(descAndTime), extractTime(descAndTime));
    }

    private static String extractDesc(String descAndTime) {
        return descAndTime.split("by")[0];
    }

    private static String extractTime(String descAndTime) {
        return descAndTime.split("by")[1];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }
}
