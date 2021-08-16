public class Deadline extends Task {

    private String Deadline;

    public Deadline(String taskName, String Deadline) {
        super(taskName);
        this.Deadline = Deadline;
    }

    public String getWhen() {
        return this.Deadline;
    }

    @Override
    public String displayInfo() {
        return String.format("[D] [%s] %s (by: %s)", this.getStatus(), this.getTaskName(), this.getWhen());
    }
}
