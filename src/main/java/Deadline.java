public class Deadline extends Task {
    private final String deadline;

    Deadline(String body, String deadline) {
        super(body, false);
        this.deadline = deadline;
    }

    Deadline(String body, boolean done, String deadline) {
        super(body, done);
        this.deadline = deadline;
    }

    @Override
    Task setDone() {
        return new Deadline(this.getBody(), true, this.deadline);
    }

    @Override
    public String toString() {
        if (this.getDone()) {
            return "[D] [X] " + this.getBody() + " (by:" + this.deadline + ")";
        }
        else {
            return "[D] [ ] " + this.getBody() + " (by:" + this.deadline + ")";
        }
    }
}
