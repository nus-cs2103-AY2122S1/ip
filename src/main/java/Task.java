public class Task {
    private final String name;
    private boolean completed;

    public Task(String name) {
        this.name = name;
        completed = false;
    }

    public String getName() {
        return this.name;
    }
    public void toggleCompleted() {
        this.completed = !this.completed;
    }
    public String toString() {
        return "[" + (completed ? "X" : " ") + "] " + this.name;
    }
}
