public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        StringBuilder taskLine = new StringBuilder();
        if (this.isDone) {
            taskLine.append("[T][x]");
        } else {
            taskLine.append("[T][ ]");
        }
        taskLine.append(this.description.replaceFirst("todo", ""));
        return taskLine.toString();
    }
}
