package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;


    protected Task() {
        this.description = "NONE";
        this.isDone = false;
    }

    /**
     * The public access constructor for inherited classes
     * to implement or override.
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getStatusInt() {
        return (isDone ? "1" : "0");
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.taskType, this.getStatusIcon(), this.description);
    }

    public String getDataString() {
        return String.format("%s_~_%s_~_%s", this.taskType, this.getStatusInt(), this.description);
    }

    public String getTaskType() {
        return this.taskType;
    }

}
