public class Deadline extends Task {
    protected String deadline;

    public Deadline(String desc, String deadline) {
        super(desc);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" 
                + super.toString()
                + " (by: "
                + this.deadline
                + ")";
    }
}
