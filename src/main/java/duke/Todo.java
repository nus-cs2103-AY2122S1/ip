package duke;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toWrite() {
        int marked = this.isDone ? 1 : 0;
        return String.format("T|%d|%s\n", marked, this.description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
