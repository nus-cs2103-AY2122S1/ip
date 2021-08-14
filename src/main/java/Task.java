public class Task {
    private String description;
    private Boolean isDone = false;

    Task(String description) {
        this.description = description;
    }

    public void setDone() {
        isDone = true;
    }

    public String toString() {
        return String.format("[%c] %s", isDone ? 'X' : ' ', description);
    }
}
