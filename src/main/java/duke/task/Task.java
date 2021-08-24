package duke.task;

public class Task {
    private final String content;
    private boolean isCompleted;

    public Task(String content) {
        this.content = content;
        this.isCompleted = false;
    }

    public boolean isComplete() {
        return this.isCompleted;
    }

    public void markComplete() {
        this.isCompleted = true;
    }

    @Override
    public String toString() {
        String cross = this.isCompleted ? "X" : " ";
        return "[" + cross + "]" + this.content;
    }

    public String toStorageString() {
        String done = isCompleted ? "1" : "0";
        String s = String.format("| %s | %s", done, content);
        return s;
    }

}
