public class Task {
    private String taskName;
    private boolean isDone;
    private boolean isEmpty = true;

    public Task() {}

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        this.isEmpty = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public boolean isEmpty() { return isEmpty; }

    public String getCheckBox() {
        if (isDone) {
            return "[X]";
        }
        return "[ ]";
    }

    @Override
    public String toString() {
        String result = this.getCheckBox() + " " + this.taskName;
        return result;
    }
}
