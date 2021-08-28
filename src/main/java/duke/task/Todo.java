package duke.task;

/** A class that represents a to-do task. */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean done) {
        super(description, done);
    }

    @Override
    public String serialize() {
        return String.join(" | ", "T", this.done ? "1" : "0", this.description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
