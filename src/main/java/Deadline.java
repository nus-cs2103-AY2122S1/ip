public class Deadline extends Task {
    private String by;

    public Deadline(String desc, String by) {
        super(desc, "D");
        this.by = by;
    }

    public String toData() {
        return super.toData() + "~S~" + by;
    }
    
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
