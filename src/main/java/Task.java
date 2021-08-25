public class Task {
    private boolean isDone;
    private String taskSummary;

    public static Task of(String taskSummary) {
        return new Task(taskSummary);
    }

    Task(String taskSummary) {
        this.taskSummary = taskSummary;
        this.isDone = false;
    }

    public void markCompleted() {
        this.isDone = true;
    }

    private String completeStatus() {
        return this.isDone ? "x" : "";
    }

    //getters
    public boolean isCompleted() {
        return this.isDone;
    }
    public String getTaskSummary() {
        return this.taskSummary;
    }
    public String toStorageFormat() {
        return String.format("? | %d | %s ",
                this.isCompleted() ? 1 : 0,this.getTaskSummary());
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.completeStatus(),this.taskSummary);
    }
}
