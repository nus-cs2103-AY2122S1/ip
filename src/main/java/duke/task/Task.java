package duke.task;

public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for Task object
     *
     * @param task Task description
     * @param isDone Completion status
     */
    public Task(String task, boolean isDone) {
        this.description = task;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns converted to string representation of completion status.
     *
     * @return String representation of completion status
     */
    public String getStatusIcon() {
        return isDone ? "[X] " : "[ ] ";
    }

    /**
     * Marks task as complete.
     *
     * @return Full string representation of task with completion status
     */
    public String check() {
        this.isDone = true;
        return this.toString();
    }

    /**
     * Marks the task as incomplete.
     *
     * @return Full string representation of task with completion status
     */
    public String uncheck() {
        this.isDone = false;
        return this.toString();
    }

    public String toString() {
        return getStatusIcon() + description;
    }

    public String toSaveString() {
        return (isDone ? "1" : "0") + "|" + this.description;
    }

    /**
     * Check if this task contains the keyword.
     *
     * @param keyword Keyword
     * @return Boolean that checks if task contains keywords
     */
    public boolean findKeyword(String keyword) {
        String processed = description;
        return processed.toLowerCase().contains(keyword);
    }
}
