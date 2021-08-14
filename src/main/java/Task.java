public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String markAsDone() {
        if (!this.isDone) {
            this.isDone = true;
            return "Fine, I'll mark it for you: " + this.toString();
        } else {
            return "I'm lazy, stop making me mark the same things again...";
        }
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
