package tasks;

public class Todo extends Task {
    private String taskName;
    private String taskSymbol = "T";

    public Todo(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        String statusIcon = getStatusIcon();
        return String.format("[%s][%s] %s", taskSymbol, statusIcon, taskName);
    }
}
