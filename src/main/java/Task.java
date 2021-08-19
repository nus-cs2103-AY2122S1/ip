public class Task {
    private boolean isDone;
    private String taskSummary;

    public static Task of(String taskSummary) {
        return new Task(taskSummary);
    }
    private Task(String taskSummary) {
        this.taskSummary = taskSummary;
        this.isDone = false;
    }

    public void isCompleted() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "x" : "",taskSummary.toString());
    }
}
