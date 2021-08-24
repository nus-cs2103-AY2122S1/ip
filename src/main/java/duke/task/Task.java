package duke.task;

import duke.io.TextColor;

public abstract class Task {
    protected boolean done;
    protected String name;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void doTask() {
        done = true;
    }

    public boolean isDone() {
        return done;
    }

    /**
     * Returns the name of the task
     *
     * @return name of the task
     */
    public String getName() {
        return name;
    }

    public abstract boolean isExpired();

    public abstract TextColor getListColor();

    @Override
    public String toString() {
        return "[" + (done ? "X" : " ") + "] " + name;
    }

    public String getSaveString() {
        return (done ? "o," : "x,") +  name;
    }
}
