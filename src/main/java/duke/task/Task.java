package duke.task;
public class Task {
    private final String description;
    private boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public Task(String isCompleted, String description) {
        this.description = description;
        this.isCompleted = isCompleted.equals("1");
    }

    public String getDescription() {
        return this.description;
    }

    public String getIsCompleted() {
        return this.isCompleted ? "1" : "0";
    }

    public String getStatusIcon() {
        return (isCompleted ? "X" : " "); // mark done task with X
    }

    public void markAsCompleted() {
        this.isCompleted = true;
    }

    public String writeToFile() {
        return String.format("TASK | %s | %s\n", getIsCompleted(), getDescription());
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
    }
}