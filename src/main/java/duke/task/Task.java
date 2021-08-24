package duke.task;

public class Task {
    String content;
    boolean isDone;

    public Task(String content) {
        this.content = content;
        this.isDone = false;
    }

    public Task(String content, boolean isDone) {
        this.content = content;
        this.isDone = isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    /**
     * Encode the task in format "t35698askType isDone content"
     * @return encoded task string
     */
    public String encode() {
        return String.format("T %b %s", isDone, content);
    }

    public String toString() {
        return String.format("[T] [%s] %s", isDone ? "X" : " ", content);
    }
}
