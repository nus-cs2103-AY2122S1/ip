package duke.task;

public class Task {
    protected int id;
    protected String description;
    protected boolean isDone;
    protected static int idCounter = 1;

    public Task() {}

    public Task(String description) {
        this.id = idCounter;
        this.description = description;
        this.isDone = false;
        idCounter++;
    }

    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    public void setDone() {
        this.isDone = true;
    }

    /**
     * Returns the description of the Task.
     *
     * @return the description String of the Task.
     */
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
