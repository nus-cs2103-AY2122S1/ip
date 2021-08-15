public class Deadline extends Task {
    private String doneBy;
    public Deadline(String name, String by) {
        super(name);
        this.doneBy = by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + doneBy + ")";
    }
}
