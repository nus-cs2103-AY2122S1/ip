public class Task {
    private String desc;
    private boolean isComplete;

    public Task(String desc) {
        this.desc = desc;
        this.isComplete = false;
    }

    public void markAsComplete() {
        this.isComplete = true;
    }

    public String toString() {
        return String.format("[%c] %s",this.isComplete ? 'X' : ' ', desc);
    }
}