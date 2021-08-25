public class Deadline extends Tasks {
    protected String by;

    public Deadline(String description) {
        super(description.substring(0, description.indexOf("/by")));
        this.by = description.substring(description.indexOf("/by") + 4);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}