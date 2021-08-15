public class Task {
    private final String name;
    private boolean completed;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    public String getName() {
        return this.name;
    }

    public void markAsDone() {
        this.completed = true;
    }

    public String print() {
        if (this.completed) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}
