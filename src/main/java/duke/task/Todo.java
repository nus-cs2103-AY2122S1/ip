package duke.task;

public class Todo extends Task {
    public Todo(boolean done, String taskName) {
        super(done, taskName);
    }

    @Override
    public String encode() {
        return String.format("T|%s", super.encode());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
