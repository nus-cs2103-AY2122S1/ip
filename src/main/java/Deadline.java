public class Deadline extends Task{

    private String by;

    public Deadline(String value, String by) {
        super(value);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

}
