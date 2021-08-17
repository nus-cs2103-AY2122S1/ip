public class Task {

    private boolean completed;
    private String taskName;

    public Task(String taskName) {
        this.completed = false;
        this.taskName = taskName;
    }

    public void markAsCompleted() {
        this.completed = true;
    }

    public boolean checkIfCompleted() {
        return this.completed;
    }

    public String getTaskName() {
        return this.taskName;
    }

    @Override
    public String toString() {
        return "[" + checkIfTaskCompleted(this) + "] " + this.getTaskName();
    }

    public static String checkIfTaskCompleted(Task currentTask) {
        return currentTask.checkIfCompleted()
                ? "✅"
                : "X";
    }
}
