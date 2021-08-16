public class Task {
    private String description;
    private boolean isCompleted;

    public Task(String description) {
        this.description = description;
        isCompleted = false;
    }

    public void markAsCompleted() {
        this.isCompleted = true;
    }

    @Override
    public String toString() {
        char marked = isCompleted ? 'X' : ' ';
        return String.format("[%c] %s", marked, this.description);
    }
}
