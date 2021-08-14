public class Task {
    private final String body;
    private final String deadline;
    private final boolean done;

    Task(String body){
        this.body = body;
        this.deadline = "";
        this.done = false;
    }

    Task(String body, String deadline) {
        this.body = body;
        this.deadline = deadline;
        this.done = false;
    }

    private Task(String body, boolean done) {
        this.body = body;
        this.deadline = "";
        this.done = done;
    }


    String getBody() {
        return this.body;
    }

    String getDeadline() {
        return this.deadline;
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
            return "[X] " + this.body;
        }
        else {
            return "[ ] " + this.body;
        }
    }
}
