public class Deadline extends Task {
    protected String by;

    public Deadline(String taskstr, String deadline) {
        super(taskstr);
        this.by = deadline;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by + ")";
    }

}
