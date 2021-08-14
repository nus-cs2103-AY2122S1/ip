import java.util.Objects;

public class Task {
    protected String description;
    protected boolean isDone;
//    protected char tag;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

//    public Task(String description, char tag) {
//        this.description = description;
//        this.isDone = false;
//        this.tag = tag;
//    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}