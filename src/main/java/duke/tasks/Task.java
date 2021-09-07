package duke.tasks;

public abstract class Task {
    protected Boolean done;
    protected String name;
    protected char type;

    public Task(String input) {
        this.name = input;
        this.done = false;
    }

    public String getStatus() {
        return (done ? "X" : " ");
    }

    public Task completeTask() {
        this.done = true;
        return this;
    }

    public abstract Task snoozeTask();

    public void setType(char type) {
        this.type = type;
    }

    public void setDone() {
        this.done = true;
    }

    public char getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + name;
    }
}
