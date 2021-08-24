public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getData() {
        return String.format("D,%s,%s", this.by, super.getData());
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }
}
