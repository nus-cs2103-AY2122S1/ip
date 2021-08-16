public class Task {
    private String name;
    private boolean isCompleted;

    public Task(String name) {
        this.name = name;
        isCompleted = false;
    }

    public void markAsCompleted() {
        this.isCompleted = true;
    }

    @Override
    public String toString() {
        char marked = isCompleted ? 'X' : ' ';
        return String.format("[%c] %s", marked, this.name);
    }
}
