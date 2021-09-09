package duke.task;

import duke.util.Priority;

/**
 * The class models tasks in real life.
 */
public class Task implements Comparable<Task> {
    protected final Priority DEFAULT_PRIORITY = Priority.MEDIUM;

    String content;
    boolean isDone;
    Priority priority;

    /**
     * Constructor.
     * Instantiates a Task object with given task content.
     *
     * @param content
     */
    public Task(String content) {
        this.content = content;
        this.isDone = false;
    }


    /**
     * Constructor.
     * Instantiates a Task object with given task content and
     * isDone status with by default medium priority.
     *
     * @param content
     * @param isDone
     */
    public Task(String content, boolean isDone) {
        this.content = content;
        this.isDone = isDone;
        priority = DEFAULT_PRIORITY;
    }


    /**
     * Constructor.
     * Instantiates a Task object with given task content and priority.
     *
     * @param content
     */
    public Task(String content, Priority priority) {
        this.content = content;
        this.isDone = false;
        this.priority = priority;
    }


    /**
     * Constructor.
     * Instantiates a Task object with given task content and
     * isDone status with by default medium priority.
     *
     * @param content
     * @param isDone
     */
    public Task(String content, boolean isDone, Priority priority) {
        this.content = content;
        this.isDone = isDone;
        this.priority = priority;
    }


    /**
     * Mark the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }


    /**
     * Check if the content of the task has the given keyword.
     *
     * @param keyword
     * @return A boolean indicating if the task contains the keyword.
     */
    public boolean containsKeyword(String keyword) {
        return content.contains(keyword);
    }


    /**
     * Encode the task in format "t35698askType isDone content"
     *
     * @return encoded task string
     */
    public String encode() {
        return String.format("T %b %s", isDone, content);
    }


    /**
     * String representation of Tasks.
     *
     * @return String representation of the task.
     */
    public String toString() {
        return String.format("[T] [%s] [%s] %s", priority, isDone ? "X" : " ", content);
    }


    /**
     * Compares with another task based on priority.
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Task o) {
        return priority.compareTo(o.priority);
    }
}
