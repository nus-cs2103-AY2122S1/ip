package duke.task;

public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor
     *
     * @param task task description
     * @param isDone completion status
     */
    public Task(String task, boolean isDone) {
        this.description = task;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    /**
     * returns converted to string representation of completion status
     *
     * @return string representation of completion status
     */
    public String getStatusIcon() {
        return isDone ? "[X] " : "[ ] ";
    }

    /**
     * marks task as complete
     *
     * @return full string representation of task with completion status
     */
    public String check() {
        this.isDone = true;
        return this.toString();
    }

    /**
     * marks the task as incomplete
     *
     * @return full string representation of task with completion status
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
     * check is this task contains the keyword
     *
     * @param keyword keyword
     * @return if task contains keywords
     */
    public boolean findKeyword(String keyword) {
        String processed = description;
        return processed.toLowerCase().contains(keyword);
    }
}
