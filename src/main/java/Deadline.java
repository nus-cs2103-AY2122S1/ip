public class Deadline extends Task {
    private String deadline;

    public Deadline(String desc, String deadline) {
        super(desc);
        this.deadline = deadline;
    }

    public Deadline(String desc, String deadline, boolean isDone) {
        super(desc, isDone);
        this.deadline = deadline;
    }

    @Override
    public String saveTask() {
        return "D|" + this.isDone() + "|" + getDesc() + "|" + deadline + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + this.statusIcon() + this.getDesc() + " (by: " + this.deadline + ")";
    }
}
