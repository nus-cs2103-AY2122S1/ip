public class Task {
    String description;
    boolean isDone;

    public Task(String task) {
        this.description = task;
        this.isDone = false;
    }

    public String check() {
        this.isDone = true;
        return this.description;
    }
}
