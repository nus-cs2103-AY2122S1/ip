public class Task {
    private String taskSummary;

    public static Task of(String taskSummary) {
        return new Task(taskSummary);
    }
    private Task(String taskSummary) {
        this.taskSummary = taskSummary;
    }

    @Override
    public String toString() {
        return taskSummary.toString();
    }
}
