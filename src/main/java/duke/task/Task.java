package duke.task;

import duke.io.TextColor;

public abstract class Task {
    protected boolean isDone;
    protected String name;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void doTask() {
        isDone = true;
    }

    public boolean isDone() {
        return isDone;
    }

    public abstract boolean isExpired();

    public abstract TextColor getListColor();

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + name;
    }

    public String getSaveString() {
        return (isDone ? "o," : "x,") +  name;
    }
}
