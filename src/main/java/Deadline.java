public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) throws IllegalArgumentException {
        super(description);
        if (by.equals("")) {throw new IllegalArgumentException();}
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}