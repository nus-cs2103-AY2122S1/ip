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

    public String print() {
        return String.format("T,%d,%s",isCompleted() ? 1 : 0, this.getName());
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.isCompleted() ? "[X]" : "[ ]", this.getName());
    }
}
