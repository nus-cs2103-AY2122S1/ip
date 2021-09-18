package duke;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String tag;

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

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return this.tag;
    }

    @Override
    public String toString () {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public String toStringForStorage() {
        return this.description;
    }

}
