public abstract class Task {
    private boolean done;
    private String name;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void doTask() {
        done = true;
    }

    public boolean isDone() {
        return done;
    }

    public abstract boolean isExpired();

    @Override
    public String toString() {
        return "[" + (done ? "X" : " ") + "] " + name;
    }
}
