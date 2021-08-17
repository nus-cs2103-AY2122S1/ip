public class Task {
    private String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getTaskName() {
        return this.taskName;
    }

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
