public class Task {
    private final String name;
    private boolean isComplete;

    Task(String name) {
        this.name = name;
        this.isComplete = false;
    }

    public String getName() {
        return name;
    }

    public boolean isComplete() {
        return this.isComplete;
    }

    public void markComplete() {
        this.isComplete = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isComplete ? "X" : " ", name);
    }
}
