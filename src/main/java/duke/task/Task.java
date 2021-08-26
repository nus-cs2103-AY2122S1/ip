package duke.task;

public class Task {
    private String name;
    private boolean isCompleted;

    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    public void markAsDone() {
        this.isCompleted = true;
    }

    public String convertToSaveFormat() {
        String completedStr = isCompleted ? "1" : "0";
        return completedStr + "|" + name;
    }

    @Override
    public String toString() {
        String symbol = isCompleted ? "X" : " ";
        return "[" + symbol + "] " + name;
    }
}
