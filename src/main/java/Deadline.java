public class Deadline extends Task {
    protected String by;
    protected String type;

    public Deadline(String information, String by,String type) {
        super(information);
        this.type = type;
        this.by = by;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}