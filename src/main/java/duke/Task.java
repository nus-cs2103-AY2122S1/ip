package duke;

public class Task {
    private String name;
    private boolean isDone;
    private String time;
    private char type;

    public Task(String name, char c, Boolean isDone) {
        this.name = name;
        this.isDone = isDone;
        this.type = c;
    }

    @Override
    public String toString() {
        return "[" + isDone() + "] " + name;
    }

    public String isDone() {
        return (isDone ? "X" : " ");
    }

    public void setDone() {
        this.isDone = true;
    }

    public String toSavedAs() {
        return (this.type + "|" + this.isDone + "|" + this.name);
    }

    public String getDescription() {
        return name;
    }
}
