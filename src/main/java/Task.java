//Imported Partial Solution
public class Task {
    enum Type {
        T,
        D,
        E,
    }

    protected String description;
    protected boolean isDone;
    protected Type type;

    public Task(String description, Type type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    //Completes the Task
    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + type.toString() + "]" + getStatusIcon() + description;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }
}
