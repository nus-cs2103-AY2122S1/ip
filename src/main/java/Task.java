public class Task {
    private final String name;
    private boolean completed;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    public Task(String name, boolean completed) {
        this.name = name;
        this.completed = completed;
    }

    public void markAsDone() {
        this.completed = true;
    }

    @Override
    public String toString() {
        if (this.completed) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}
