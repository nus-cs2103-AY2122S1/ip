package duke.task;

public abstract class Task {

    protected String description;

    protected boolean isDone;

    /**
     * Constructor for Task
     * @param description String containing description of Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public abstract void update(String userInput);

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done duke.task with X
    }

    public void markAsDone() {
        this.isDone = true; }

    public boolean doesDescriptionContain(String query) {
        return this.description.contains(query);
    }

    public String getData() {
        return String.format("%b,%s", this.isDone, this.description);
    }

    /**
     * Returns the string representation of a Task.
     *
     * @return String representing a task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

}
