package duke.tasks;

public class Task {

    private String name;
    private boolean isDone;

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public String getDoneMarker() {
        return this.isDone ? "X" : " ";
    }

    public String getDone() {
        return isDone ? "1" : "0";
    }

    public String getMarker() {
        return " ";
    }

    public String getTime() {
        return " ";
    }

    public String getName() {
        return this.name;
    }

    public void completeTask() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getDoneMarker() + "] " + this.getName();
    }

}
