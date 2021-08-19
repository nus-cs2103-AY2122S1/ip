public class Task {
    private boolean completed;
    private final String name;

    public Task(String name) {
        this.name = name;
    }

    public String completeTask() {
        this.completed = true;
        return this.toString();
    }

    @Override
    public String toString() {
        if (this.completed) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }
}
