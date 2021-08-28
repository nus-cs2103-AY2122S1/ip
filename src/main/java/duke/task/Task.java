package duke.task;

public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructor for task.
     *
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Checks if description contains the keyword.
     *
     * @param keyword keyword to match
     */
    public boolean matchDescription(String keyword) {
        return description.contains(keyword);
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}
