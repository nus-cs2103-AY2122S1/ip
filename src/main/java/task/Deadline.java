package task;

public class Deadline extends Task {

    public final static String SPLITTER = "/by";

    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getFormattedBy() {
        return " (" + SPLITTER.substring(1)  + ": " + by + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + getFormattedBy();
    }
}
