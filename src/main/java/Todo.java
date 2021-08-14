public class Todo extends Task {

    Todo (String body) {
        super(body, false);
    }

    Todo(String body, boolean done) {
        super(body, done);
    }

    @Override
    Task setDone() {
        return new Todo(this.getBody(), true);
    }

    @Override
    public String toString() {
        if (this.getDone()) {
            return "[T] [X]" + this.getBody();
        }
        else {
            return "[T] [ ]" + this.getBody();
        }
    }
}
