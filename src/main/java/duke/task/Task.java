package duke.task;

/**
 * The class models tasks in real life.
 */
public class Task {
    String content;
    boolean isDone;

    /**
     * Constructor.
     * Instantiates a Task object with given task content.
     * @param content
     */
    public Task(String content) {
        this.content = content;
        this.isDone = false;
    }

    /**
     * Constructor.
     * Instantiates a Task object with given task content and
     * isDone status.
     * @param content
     * @param isDone
     */
    public Task(String content, boolean isDone) {
        this.content = content;
        this.isDone = isDone;
    }

    /**
     * Mark the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Check if the content of the task has the given keyword.
     * @param keyword
     * @return
     */
    public boolean containsKeyword(String keyword) {
        return content.contains(keyword);
    }

    /**
     * Encode the task in format "t35698askType isDone content"
     * @return encoded task string
     */
    public String encode() {
        return String.format("T %b %s", isDone, content);
    }

    /**
     * String representation of Tasks.
     * @return String representation of the task.
     */
    public String toString() {
        return String.format("[T] [%s] %s", isDone ? "X" : " ", content);
    }
}
