package task;

public class Deadline extends Task {

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
        return " (by: " + by + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + getFormattedBy();
    }
}
