public class Deadline extends Task {

    private String deadline;

    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    public String getWhen() {
        return this.deadline;
    }

    @Override
    public String displayInfo() {
        return String.format("[D] [%s] %s (by: %s)", this.getStatus(), this.getTaskName(), this.getWhen());
    }
}
