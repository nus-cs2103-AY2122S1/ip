public class Deadline extends Task {

    protected String by;

    public Deadline(String detail, String by) {
        super(detail);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
