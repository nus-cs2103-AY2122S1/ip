package duke;

public class Task {
    private final String body;
    private final boolean isDone;

    Task(String body){
        this.body = body;
        this.isDone = false;
    }

    Task(String body, boolean done) {
        this.body = body;
        this.isDone = done;
    }

    String getBody() {
        return this.body;
    }

    boolean isDone() {
        return this.isDone;
    }

    Task setDone() {
        return new Task(this.body, true);
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return " [X] " + this.body;
        } else {
            return " [ ] " + this.body;
        }
    }
}
