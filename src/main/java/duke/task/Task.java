package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected char category;
    protected String name;

    public Task(String description, char category) {
        this.description = description;
        this.isDone = false;
        this.category = category;
        this.name = "";
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markDone() {
        this.isDone = true;
    }

    public String getDesc() {
        return this.description;
    }

    public char getCat() {
        return this.category;
    }

    /**
     * Returns task name.
     *
     * @return Name of task.
     */
    public String getName() {
        return this.name;
    }

    public String getDueTime() {
        return "";
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
