public class Deadline extends Task {
    protected String toCompleteBy;
    protected String taskType;

    public Deadline(String description, String toCompleteBy) {
        super(description);
        this.toCompleteBy = toCompleteBy;
        this.taskType = "D";
    }

    @Override
    public String getTaskType() {
        return this.taskType;
    }

    @Override
    public void markAsDone() {
        this.isDone = true;
        System.out.println("[" + this.taskType + "][" + this.getStatusIcon() + "] " + this.description);
    }
}
