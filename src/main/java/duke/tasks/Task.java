package duke.tasks;

public abstract class Task {
    protected String taskName;
    protected boolean done;

    public Task(String taskName) {
        done = false;
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", done ? "X" : " ", taskName);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        Task t = (Task) o;
        return taskName.equals(t.taskName) && done == t.isDone();
    }

    public void markDone() {
        done = true;
    }

    public boolean isDone() {
        return done;
    }

    public abstract String getIdentifier();

    public abstract String getDetailsWithDelimiter(String delimiter);


}
