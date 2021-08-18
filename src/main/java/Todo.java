public class Todo extends Task {
    protected String taskType;

    public Todo(String description) {
        super(description);
        this.taskType = "T";
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
