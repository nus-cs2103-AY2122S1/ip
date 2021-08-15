public class Task {
    private final String name;
    private boolean completed;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    public void markAsDone() {
        this.completed = true;
    }

    @Override
    public String toString() {
        return (this.completed ? "[X] " : "[ ] " ) + this.name;
    }
}
