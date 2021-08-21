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

    public String getName() {
        return this.name;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    @Override
    public String toString() {
        return (this.completed ? "[X] " : "[ ] " ) + this.name;
    }

    public String print() {
        return String.format("T,%d,%s",isCompleted() ? 1 : 0, this.getName());
    }
}
