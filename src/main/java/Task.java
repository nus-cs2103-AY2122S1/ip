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

    //getters
    public boolean isCompleted() {
        return this.isDone;
    }
    public String getTaskSummary() {
        return this.taskSummary;
    }

//    public abstract String giveTaskCategory();

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "x" : "",this.taskSummary);
    }
}
