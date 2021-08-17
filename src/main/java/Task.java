public class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + name;
    }

}
