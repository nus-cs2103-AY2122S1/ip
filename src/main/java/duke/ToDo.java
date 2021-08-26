package duke;

public class ToDo extends Task{

    public ToDo(String task) {
        super(task, "T");
    }

    public ToDo(String task, boolean completed) {
        super(task, completed,"T");
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.getTaskType(), this.getCompletedMarker(), this.getTask());
    }

    @Override
    public String parseForStorage() {
        return String.format("%s | %d | %s", this.getTaskType(), this.getCompleted() ? 1 : 0, this.getTask());
    }
}
