package duke.task;

/**
 * Encapsulates a task in DukeList.
 */
public class Task {
    private boolean completed;
    protected String name;

    public Task(String name) {
        this.completed = false;
        this.name = name;
    }

    /**
     * Mark the task as completed.
     */
    public void markCompleted() {
        this.completed = true;
    }

    /**
     * Returns whether the task is completed.
     */
    public boolean isCompleted() {
        return this.completed;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task)
            return ((Task) obj).name.equals(this.name);
        return false;
    }

    @Override
    public String toString() {
        return "[" + (this.completed ? "X" : " ") + "] " + this.name;
    }
}
