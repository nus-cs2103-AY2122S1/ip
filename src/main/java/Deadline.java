public class Deadline extends Task {
    private String deadline;

    public Deadline(String desc, String deadline) {
        super(desc);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + this.statusIcon() + this.getDesc() + " (by: " + this.deadline + ")";
    }
}
