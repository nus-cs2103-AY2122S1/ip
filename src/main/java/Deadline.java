public class Deadline extends Task{
    protected String by;

    public Deadline(String[] desc) {
        super(desc[1], false);
        this.by = desc[2];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")\n";
    }
}
