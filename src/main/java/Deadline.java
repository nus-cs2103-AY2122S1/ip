public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description, 'D');
        this.by = by;
    }

    @Override
    public String getDueTime() {
        return by;
    }

    @Override
    public String toString() {
        return "[" + this.getCat() + "]" + super.toString() + " (by: " + by + ")";
    }
}