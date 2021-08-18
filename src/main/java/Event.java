public class Event extends Task {
    protected String details;
    protected String taskType;

    public Event(String description, String details) {
        super(description);
        this.details = details;
        this.taskType = "E";
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
