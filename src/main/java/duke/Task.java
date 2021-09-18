package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     *
     */
    public String getDescription() {
        return this.description;
    }

    public void markAsDone () {
        this.isDone = true;
    }

    @Override
    public String toString () {
        return "[" + getStatusIcon() + "] " + this.description;
    }

}
