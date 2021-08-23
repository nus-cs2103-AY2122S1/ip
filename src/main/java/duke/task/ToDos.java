package duke.task;

public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T] [%s] " + this.description, this.getStatusIcon());
    }

    @Override
    public String toDataFileString() {
        return String.format("T|%s|%s", this.isDone ? "1" : "0", this.description);
    }
}
