public class Deadline extends Task {
    private String deadline;

    public Deadline(String taskname, String deadline) {
        super(taskname);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return "Deadline: " + super.toString() + " (by" + this.deadline + ")";
    }
}
