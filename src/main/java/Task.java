public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    private String status() {
        return this.isDone ? "X" : " ";
    }


    @Override
    public String toString() {
        return String.format("[%s] ", this.status()) + description;
    }
}
