public class Task {
    private String name;
    private boolean isCompleted = false;

    Task(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    public void setCompleted() {
        this.isCompleted = true;
    }
}
