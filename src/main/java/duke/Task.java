package duke;

public class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.name);
    }

    // for saving to local file
    public String toSaveString() {
        return this.name + "~" + (isDone ? "1" : "0");
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isFound(String target) {
        return this.name.contains(target);
    }
}