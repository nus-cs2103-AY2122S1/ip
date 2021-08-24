package duke.tasks;

public class Task {
    protected final String description;
    protected final String dateTime;
    protected final boolean isDone;

    public Task(String description) {
        this.description = description;
        this.dateTime = "";
        this.isDone = false;
    }

    public Task(String description, String dateTime) {
        this.description = description;
        this.dateTime = dateTime;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.dateTime = "";
        this.isDone = isDone;
    }

    public Task(String description, String dateTime, boolean isDone) {
        this.description = description;
        this.dateTime = dateTime;
        this.isDone = isDone;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getSymbol() {
        return "parent-task";
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public Task markAsDone() {
        return new Task(this.getDescription(), true);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
