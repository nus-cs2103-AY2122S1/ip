public class Deadlines extends Task{
    protected String by;

    public Deadlines(String description, String by) {
        super(description, Instruction.DEADLINE);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
