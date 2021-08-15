public class Task {

    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void setCompleted() {
        this.isDone = true;
    }

    public String toString() {
        if (isDone) {
            return "[x] " + name;
        } else {
            return "[ ] " + name;
        }
    }

}
