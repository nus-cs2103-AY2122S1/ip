public class Task {
    private final String body;
    private final boolean done;

    Task(String body){
        this.body = body;
        this.done = false;
    }

    Task(String body, boolean done) {
        this.body = body;
        this.done = done;
    }


    String getBody() {
        return this.body;
    }

    boolean getDone() {
        return this.done;
    }

    Task setDone() {
        return new Task(this.body, true);
    }

    @Override
    public String toString() {
        if (this.done) {
            return " [X] " + this.body;
        }
        else {
            return " [ ] " + this.body;
        }
    }
}
