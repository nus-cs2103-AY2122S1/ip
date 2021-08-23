public class Deadline extends Task{
    private String deadline;

    public Deadline(String taskName, boolean doneStatus, String deadline) {
        super(taskName, doneStatus);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.deadline + ")";
    }
}
